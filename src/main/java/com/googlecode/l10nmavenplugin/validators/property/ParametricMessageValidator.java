/*******************************************************************************
 * Copyright (c) 2012 Romain Quinio (http://code.google.com/p/l10n-maven-plugin)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
package com.googlecode.l10nmavenplugin.validators.property;

import java.text.MessageFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.googlecode.l10nmavenplugin.log.L10nValidatorLogger;
import com.googlecode.l10nmavenplugin.model.L10nReportItem;
import com.googlecode.l10nmavenplugin.model.L10nReportItem.Type;
import com.googlecode.l10nmavenplugin.model.Property;
import com.googlecode.l10nmavenplugin.validators.AbstractL10nValidator;
import com.googlecode.l10nmavenplugin.validators.L10nValidator;
import com.googlecode.l10nmavenplugin.validators.family.ParametricCoherenceValidator;

/**
 * Validator to check syntax of parametric resources, i.e properties containing formatting parameters ({0},{1},...).
 * 
 * It will detect single quotes that are not escaped (typical usage in French, Italian, and also English). Also performs a format, to check syntax is correct.
 * 
 * Note: parametric js resource, where parametric replacement is done on client side, should also consume the single quote escaping (i.e follow
 * {@link java.text.MessageFormat} specification), otherwise the validator will raise false positive.
 * 
 * @author romain.quinio
 * @since 1.0
 * @see {@link com.googlecode.l10nmavenplugin.validators.family.ParametricCoherenceValidator}
 * 
 */
public class ParametricMessageValidator extends AbstractL10nValidator implements L10nValidator<Property> {

  /**
   * Validation of single quotes escaping, consumed by MessageFormat. First unescaped quote is matched, with special case where it is at the end
   * 
   * "^([^']|'')*$" runs into StackOverflow for long messages.
   */
  private static final String UNESCAPED_QUOTE_REGEX = "(.*[^']'[^'].*|^'[^'].*|.*[^']'$)";

  /**
   * Number of formatting parameters replaced in resources
   */
  private static final int NB_MAX_FORMAT_PARAM = 19;

  /**
   * Values to replace parameters {i} in properties.
   * 
   * Use Integers as they work with all parameter definitions {i,date} {i,number} etc.
   */
  private static final Object[] PARAMETRIC_REPLACE_VALUES = new Integer[NB_MAX_FORMAT_PARAM];

  static {
    for (int i = 0; i < NB_MAX_FORMAT_PARAM; i++) {
      PARAMETRIC_REPLACE_VALUES[i] = i;
    }
  }

  protected static final Pattern UNESCAPED_QUOTE_PATTERN = Pattern.compile(UNESCAPED_QUOTE_REGEX, Pattern.DOTALL);

  public ParametricMessageValidator(L10nValidatorLogger logger) {
    super(logger);
  }

  /**
   * ERROR single quotes are not escaped, while some parameters are present.
   * 
   * WARN escaped single quotes while resource is not parametric.
   */
  public int validate(Property property, List<L10nReportItem> reportItems) {
    int nbErrors = 0;

    // boolean isParametric = this.captureParameters(key, message, propertiesName);
    boolean isParametric = ParametricCoherenceValidator.isParametric(property.getMessage());
    if (isParametric) {
      Matcher unescapedQuotesMatcher = UNESCAPED_QUOTE_PATTERN.matcher(property.getMessage());
      if (unescapedQuotesMatcher.matches()) {
        nbErrors++;
        L10nReportItem reportItem = new L10nReportItem(Type.UNESCAPED_QUOTE_WITH_PARAMETERS, "MessageFormat requires that ' be escaped with ''.", property,
            null);
        reportItems.add(reportItem);
        logger.log(reportItem);
      }
    } else if (property.getMessage().contains("''")) {
      L10nReportItem reportItem = new L10nReportItem(Type.ESCAPED_QUOTE_WITHOUT_PARAMETER,
          "Resource contains escaped quote '' but no parameters. This may be correct if formatter is called with unused parameters.", property, null);
      reportItems.add(reportItem);
      logger.log(reportItem);
    }
    return nbErrors;
  }

  public static String defaultFormat(String message) {
    return MessageFormat.format(message, PARAMETRIC_REPLACE_VALUES);
  }

  public boolean shouldValidate(Property property) {
    // Always validate
    return true;
  }
}
