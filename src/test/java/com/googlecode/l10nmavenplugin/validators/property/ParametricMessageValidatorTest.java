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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.l10nmavenplugin.model.Property;
import com.googlecode.l10nmavenplugin.model.PropertyImpl;
import com.googlecode.l10nmavenplugin.validators.AbstractL10nValidatorTest;

public class ParametricMessageValidatorTest extends AbstractL10nValidatorTest<Property> {

  @Override
  @Before
  public void setUp() {
    super.setUp();
    validator = new ParametricMessageValidator(logger);
  }

  @Test
  public void testUnescapedQuotesPattern() {
    assertFalse(ParametricMessageValidator.UNESCAPED_QUOTE_PATTERN.matcher("Some text").matches());
    assertFalse(ParametricMessageValidator.UNESCAPED_QUOTE_PATTERN.matcher("Some '' text").matches());
    assertFalse(ParametricMessageValidator.UNESCAPED_QUOTE_PATTERN.matcher("Some text''").matches());
    assertFalse(ParametricMessageValidator.UNESCAPED_QUOTE_PATTERN.matcher("''Some text").matches());
    assertFalse(ParametricMessageValidator.UNESCAPED_QUOTE_PATTERN.matcher("Some ''{0}'' text").matches());

    assertTrue(ParametricMessageValidator.UNESCAPED_QUOTE_PATTERN.matcher("Some ' text").matches());
    assertTrue(ParametricMessageValidator.UNESCAPED_QUOTE_PATTERN.matcher("Some text'").matches());
    assertTrue(ParametricMessageValidator.UNESCAPED_QUOTE_PATTERN.matcher("'Some text'").matches());
    assertTrue(ParametricMessageValidator.UNESCAPED_QUOTE_PATTERN.matcher("Some '' text '").matches());
    // TODO assertFalse(ParametricMessageValidator.UNESCAPED_QUOTE_PATTERN.matcher("Some 'quoted' text").matches());
  }

  @Test
  public void testEscapedQuote() {
    assertEquals(0, validator.validate(new PropertyImpl(KEY_OK, "Some text", FILE), items));
    assertEquals(0, validator.validate(new PropertyImpl(KEY_OK, "Some ' text", FILE), items));
    assertEquals(0, validator.validate(new PropertyImpl(KEY_OK, "Some '' {0} text", FILE), items));
    assertEquals(0, validator.validate(new PropertyImpl(KEY_OK, "Some ''{0}'' text", FILE), items));
  }

  @Test
  public void testUnescapedQuote() {
    assertEquals(1, validator.validate(new PropertyImpl(KEY_KO, "Some' text{1}", FILE), items));
    assertEquals(1, validator.validate(new PropertyImpl(KEY_KO, "Some {0} 'text", FILE), items));
    assertEquals(1, validator.validate(new PropertyImpl(KEY_KO, "<{0,date}> != '#price':$", FILE), items));
  }

  @Test
  public void testUnnecessaryQuoteEscaping() {
    // Only a warning
    assertEquals(0, validator.validate(new PropertyImpl(KEY_KO, "Some '' text", FILE), items));
    assertEquals(1, items.size());
  }

  /**
   * Check regexp on longer text for StackOverFlowError
   */
  @Test
  public void testLongMessage() {
    String longMessage = "Lorem ipsum dolor sit amet {0}, consectetur adipiscing elit. Quisque dapibus iaculis erat, ut sollicitudin odio pulvinar non. Maecenas scelerisque mi at ipsum mattis et aliquet nisi iaculis. Mauris faucibus ligula sit amet erat rhoncus consectetur. "
        + "Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. {1}"
        + "Nam sollicitudin feugiat purus, id tempus nulla pretium eu. "
        + "Morbi velit mi, porta sed tempus commodo, lobortis vel purus. {1} Fusce sed molestie purus. "
        + "Fusce leo elit, euismod accumsan elementum vitae, porta vitae nunc. "
        + "Mauris venenatis, nisi eget suscipit accumsan, est est sollicitudin risus, vitae faucibus massa massa vitae ipsum. "
        + "Fusce volutpat mattis porttitor. {2} Sed lobortis, mi at vehicula tristique, tortor ante iaculis felis, vitae mollis libero ipsum ac lectus. "
        + "Nullam eros libero, tempor in venenatis sit amet, molestie elementum risus. {3} Nullam fermentum justo vel turpis tristique {4} congue. "
        + "Nulla {5} velit lorem, ultricies nec tempus laoreet, {6} congue at lorem. ";

    assertEquals(0, validator.validate(new PropertyImpl(KEY_OK, longMessage, FILE), items));
    assertEquals(0, items.size());
  }

}
