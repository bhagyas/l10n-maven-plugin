/*******************************************************************************
 * Copyright (c) 2012 Romain Quinio (http://code.google.com/p/l10n-maven-plugin)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
package com.googlecode.l10nmavenplugin.validators;

import java.util.List;
import java.util.Set;

import com.googlecode.l10nmavenplugin.log.L10nValidatorLogger;
import com.googlecode.l10nmavenplugin.validators.L10nReportItem.*;

/**
 * Default validator, used in case no other specific validator was triggered.
 * 
 * @author romain.quinio
 *
 */
public class DefaultValidator implements L10nValidator {
  
  private L10nValidatorLogger logger;
  
  public DefaultValidator(L10nValidatorLogger logger) {
    this.logger = logger;
  }

  /**
   * Warn if other resources contain HTML/URL.
   * 
   * @param key
   * @param message
   * @param propertyName
   * @return Number of errors
   */
  public int validate(String key, String message, String propertiesName, List<L10nReportItem> reportItems) {
    int nbErrors = 0;
    if (TextValidator.isHtml(message)) {
      L10nReportItem reportItem = new L10nReportItem(Severity.WARN, 
          Type.UNDECLARED_HTML_RESOURCE, 
          "Resource may contain HTML, but is not declared as such. No validation was performed.",
          propertiesName, key, message, null);
      reportItems.add(reportItem);
      logger.log(reportItem);

    } else if (TextValidator.isUrl(message)) {
      L10nReportItem reportItem = new L10nReportItem(Severity.WARN, 
          Type.UNDECLARED_URL_RESOURCE, 
          "Resource may contain URL, but is not declared as such. No validation was performed.",
          propertiesName, key, message, null);
      reportItems.add(reportItem);
      logger.log(reportItem);
    }
    return nbErrors;
  }

  public int report(Set<String> propertiesNames, List<L10nReportItem> reportItem) {
    return 0;
  }
}
