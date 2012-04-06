/*******************************************************************************
 * Copyright (c) 2012 Romain Quinio (http://code.google.com/p/l10n-maven-plugin)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
package com.googlecode.l10nmavenplugin;

import java.io.File;

/**
 * Interface for l10 validation/report Mojo configuration
 * 
 * @author romain.quinio
 * 
 */
public interface L10nValidationConfiguration {

  public void setPropertyDir(File propertyDir);

  public File getPropertyDir();

  public void setExcludedKeys(String[] excludedKeys);

  public String[] getExcludedKeys();

  public void setIgnoreFailure(boolean ignoreFailure);

  public boolean getIgnoreFailure();

  public void setJsKeys(String[] jsKeys);

  public String[] getJsKeys();

  public void setJsDoubleQuoted(boolean jsDoubleQuoted);

  public boolean getJsDoubleQuoted();

  public void setUrlKeys(String[] urlKeys);

  public String[] getUrlKeys();

  public void setHtmlKeys(String[] htmlKeys);

  public String[] getHtmlKeys();

  public void setXhtmlSchema(File xhtmlSchema);

  public File getXhtmlSchema();

  public void setTextKeys(String[] textKeys);

  public String[] getTextKeys();

  public void setCustomPatterns(CustomPattern[] customPatterns);

  public CustomPattern[] getCustomPatterns();
}
