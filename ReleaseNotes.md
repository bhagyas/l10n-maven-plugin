**1.8** _30-Nov-2014_
  * [Issue #8](https://code.google.com/p/l10n-maven-plugin/issues/detail?id=#8) Change absolute URI in xhtml1 schemas to relative URI, in order to avoid re-downloading xml.xsd each time.
  * [Issue #11](https://code.google.com/p/l10n-maven-plugin/issues/detail?id=#11) Make plugin threadsafe for maven 3.x parallel execution
  * [Issue #12](https://code.google.com/p/l10n-maven-plugin/issues/detail?id=#12) Support for property substitution (i.e. property = ${anotherProperty}) in property values.

**1.7** _15-Nov-2013_
  * [Issue #1](https://code.google.com/p/l10n-maven-plugin/issues/detail?id=#1) Support for String.format parameters (C-stytle formatting)
  * [Issue #4](https://code.google.com/p/l10n-maven-plugin/issues/detail?id=#4) Support for multi-line property value with parameters and quotes
  * [Issue #5](https://code.google.com/p/l10n-maven-plugin/issues/detail?id=#5) Support for multiple resource bundles per project
  * [Issue #7](https://code.google.com/p/l10n-maven-plugin/issues/detail?id=#7) Support for detecting duplicated keys in property file

**1.6** _07-Jan-2013_
  * Added French translation for the plugin
  * Fixed a bug with a simple quote following a double quote in the HTML validation
  * Allowed LI elements at the root of an HTML resource (without a UL or OL encompassing element)

**1.5** _23-Sep-2012_
  * Added generation of CSV files with keys for each severity (ERROR/WARN/INFO).
  * Added a summary to the report, with links to sections
  * Added logging of expected html/url key pattern for default validator
  * Refactored ValidateMojo logic by splitting the class into 6 orchestrators
  * Fixed strange bug where javascript validator would not log errors into the console
  * Fixed HTML tag coherence validator to ignore siblings order
  * Upgraded xhtml5 schema to XMLmind 1.0.3
  * Generate automatically a [help goal](Usage#l10n:help.md) at build time

**1.4** _26-May-2012_ New validators

  * Refactored validators architecture and model, with notion of scope (single property value, translations of a property, whole properties file, whole bundle of properties file, ...)
  * Added identical translation validator, to info identically translated resources, or warn if translations are mostly identical but a few differ.
  * Added Trailing [whitespace](http://docs.oracle.com/javase/6/docs/api/java/lang/Character.html#isWhitespace%28char%29) validator for all resources.
  * Added SpellCheck validator, based on [Jazzy Spellchecker](http://sourceforge.net/projects/jazzy/) and using locale from properties file name. Location of dictionaries has to be specified via plugin configuration.
  * Added HTML tag coherence validator, to warn if html tags used are different between translated values of a resource.
  * Modified URL validator to detect HTML imported urls with hardcoded http protocol (.js, .css, .png, ...) that would cause browser mixed content warnings.
  * Modified HTML validator to ignore [HTML5 data-\* custom attributes](http://dev.w3.org/html5/spec/elements.html#embedding-custom-non-visible-data), as this cannot easily be expressed in an XML schema.
  * Added threshold (30) for number of info/warn issues logged per type.
  * Added skip plugin configuration property.
  * Added plugin integration tests using [maven-invoker-plugin](https://maven.apache.org/plugins/maven-invoker-plugin/).

**1.3** _07-Apr-2012_ Customization
  * Added [custom pattern](CustomPatterns.md) validators.
  * Added XHTML validation schema configuration, with pre-defined xhtml1-transitional (default), xhtml1-strict or xhtml5 (based on [XMLmind 1.0.1 schema](http://www.xmlmind.com/xhtml5_resources.shtml)).
  * Added configuration to switch from double quoted js resources (default) to single quoted js resources validation.
  * Modified URL validation regex to use more precise ESAPI pattern.
  * Increased number of replaced formatting parameters in resource before URL/XHTML validation from 9 to 19.
  * Fixed report generation failure when no issues found.

**1.2.1** _14-Feb-2012_ Java 5 compatibility patch
  * Fixed Java 5 compatibility issue introduced in 1.2.

**1.2** _14-Feb-2012_ Reporting goal
  * Added [report goal](Usage#l10n:report.md) that binds to site phase;
  * Added parametric replacement checks (single quote escaping, parameters coherency).
  * Added support for javascript escaping of double quotes in js resources (\\").
  * Added javascript unescaping before HTML validation for js resources.
  * Added missing translation checks.
  * Refactored code architecture with validators.
  * Changed logging to be more consistent.

**1.1** _10-Feb-2012_
  * Added HTML unescaping before URL validation.
  * Added Text key validation (no HTML/URL).
  * Changed Other resources validation to warning instead of error.

**1.0** _07-Feb-2012_ Initial release
  * [Validate goal](Usage#l10n:validate.md) that binds to test phase.