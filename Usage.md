List of goals:



# l10n:validate #
  * Validates a set of properties files at build time.
  * Full name: com.googlecode.l10n-maven-plugin:l10n-maven-plugin:1.8:validate
  * Since version 1.0
  * Binds by default to the lifecycle _test_.

| **Parameter name** | **Type** | **Since** | **Description** |
|:-------------------|:---------|:----------|:----------------|
| **propertyDir**    | `File`   | 1.0       | Directory containing properties file to check. Default value is _src\main\resources_. In case of multiple resource bundles, each group of files belonging to the same bundle will be analysed independently |
| **excludedKeys**   | `String[]` | 1.0       | Keys excluded from validation. Default is none.|
| **ignoreFailure**  | `boolean` | 1.0       | Makes validation errors not blocking the build. Default is _false_.|
| **jsKeys**         | `String[]` | 1.0       | List of keys to match as text resources used from javascript. Default is _".js."_.|
| **urlKeys**        | `String[]` | 1.0       | List of keys to match as url resources. Default is _".url."_.|
| **htmlKeys**       | `String[]` | 1.0       | List of keys to match as html text resources. Default is _".text."_.|
| **textKeys**       | `String[]` | 1.1       | List of keys to match as non-html text resources. Default is _".title."_.|
| **jsDoubleQuoted** | `boolean` | 1.3       | Declares how the client side resources are loaded in javascript. Default is _true_ (double quoted). |
| **xhtmlSchema**    | `File`   | 1.3       | XML Schema to use for html resource validation. Default value is to use _xhtml1-transitional.xsd_. Other pre-defined schema are: xhtml5.xsd and xhtml1-strict.xsd |
| **customPatterns** | `CustomPattern[]` | 1.3       | Custom validation patterns. See [CustomPatterns](CustomPatterns.md). |
| **dictionaryDir**  | `File`   | 1.4       | Directory containing dictionaries for SpellCheck validation. It defaults to same value as `propertyDir` The dictionaries (1 word perline) should be named name\_language\_country\_variant.dic (ex: en.dic, common\_en\_GB.dic) |
| **skip**           | `boolean` | 1.4       | Flag allowing to skip plugin exceution for a particular build. This makes the plugin more controllable from profiles. |
| **reportsDir**     | `File`   | 1.5       | Base directory where all reports are written to. Default is _${project.build.directory}/l10n-reports_ |
| **formatter**      | `String` | 1.7       | Formatter to use for validating resource parametric replacement. Possible values are: messageFormat (default), C-style |
| **innerResourceRegex** | `String` | 1.8       | Regex used for property substitution (i.e. property = ${anotherProperty}). Default is _""_ (no substitution). |

Note: default maven log level is INFO. It is possible to display only ERROR level with --quiet parameter, or activate DEBUG with -X parameter.

A sample configuration is shown below (showing plugin default values):

```
<build>
    <plugins>
      ...
      <plugin>
        <groupId>com.googlecode.l10n-maven-plugin</groupId>
        <artifactId>l10n-maven-plugin</artifactId>
        <version>1.8</version>
        <executions>
          <execution>
            <id>validate-resources</id>
            <phase>test</phase>
            <goals>
              <goal>validate</goal>
            </goals>
            <configuration>
              <propertyDir>src\main\resources</propertyDir>
              <ignoreFailure>false</ignoreFailure>
              <htmlKeys>
                <param>.text.</param>
              </htmlKeys>
              <xhtmlSchema>xhtml1-transitional.xsd</xhtmlSchema>
              <jsKeys>
                <param>.js.</param>
              </jsKeys>
              <jsDoubleQuoted>true</jsDoubleQuoted>
              <urlKeys>
                <param>.url.</param>
              </urlKeys>
              <textKeys>
                <param>.title.</param>
              </textKeys>
              <customPatterns />
              <excludedKeys />
              <dictionaryDir>src\main\resources</dictionaryDir>
              <reportsDir>${project.build.directory}/l10n-reports</reportsDir>
              <formatter>messageFormat</formatter>
              <innerResourceRegex />
            </configuration>
          </execution>
        </executions>
      </plugin>
      ...
    </plugins>
  </build>
```

# l10n:report #
  * Build a report on validation of a set of properties files. See [an example](http://wiki.l10n-maven-plugin.googlecode.com/git/SampleReport.html)
  * Full name: com.googlecode.l10n-maven-plugin:l10n-maven-plugin:1.8:report
  * Since version 1.2
  * Binds by default to the lifecycle _site_.

| **Parameter name** | **Type** | **Since** | **Description** |
|:-------------------|:---------|:----------|:----------------|
| **propertyDir**    | `File`   | 1.2       | Directory containing properties file to check. Default value is _src\main\resources_. In case of multiple resource bundles, each group of files belonging to the same bundle will be analysed independently |
| **jsKeys**         | `String[]` | 1.2       | List of keys to match as text resources used from javascript. Default is _".js."_.|
| **urlKeys**        | `String[]` | 1.2       | List of keys to match as url resources. Default is _".url."_.|
| **htmlKeys**       | `String[]` | 1.2       | List of keys to match as html text resources. Default is _".text."_.|
| **textKeys**       | `String[]` | 1.2       | List of keys to match as non-html text resources. Default is _".title."_.|
| **jsDoubleQuoted** | `boolean` | 1.3       | Declares how the client side resources are loaded in javascript. Default is _true_ (double quoted). |
| **xhtmlSchema**    | `File`   | 1.3       | XML Schema to use for html resource validation. Default value is to use _xhtml1-transitional.xsd_. Other pre-defined schema are: xhtml5.xsd and xhtml1-strict.xsd |
| **customPatterns** | `CustomPattern[]` | 1.3       | Custom validation patterns. See [CustomPatterns](CustomPatterns.md). |
| **dictionaryDir**  | `File`   | 1.4       | Directory containing dictionaries for SpellCheck validation. It defaults to same value as `propertyDir` The dictionaries (1 word perline) should be named name\_language\_country\_variant.dic (ex: en.dic, common\_en\_GB.dic) |
| **formatter**      | `String` | 1.7       | Formatter to use for validating resource parametric replacement. Possible values are: messageFormat (default), C-style |
| **innerResourceRegex** | `String` | 1.8       | Regex used for property substitution (i.e. property = ${anotherProperty}). Default is _""_ (no substitution). |

Note: _excludedKeys_ and _ignoreFailure_ configuration properties are not used in reporting mode.

A sample configuration is shown below (showing plugin default values):

```
<reporting>
    <outputDirectory>${basedir}/target/site</outputDirectory>
    <excludeDefaults>true</excludeDefaults>
    <plugins>
      ...
      <plugin>
        <groupId>com.googlecode.l10n-maven-plugin</groupId>
        <artifactId>l10n-maven-plugin</artifactId>
        <version>1.8</version>
        <reportSets>
          <reportSet>
            <id>generate-report</id>
            <reports>
              <report>report</report>
            </reports>
            <inherited>true</inherited>
            <configuration>
              <propertyDir>src\main\resources\</propertyDir>
              <htmlKeys>
                <param>.text.</param>
              </htmlKeys>
              <xhtmlSchema>xhtml1-transitional.xsd</xhtmlSchema>
              <jsKeys>
                <param>.js.</param>
              </jsKeys>
              <jsDoubleQuoted>true</jsDoubleQuoted>
              <urlKeys>
                <param>.url.</param>
              </urlKeys>
              <textKeys>
                <param>.title.</param>
              </textKeys>
              <customPatterns />
              <dictionaryDir>src\main\resources</dictionaryDir>
              <formatter>messageFormat</formatter>
              <innerResourceRegex />
            </configuration>
          </reportSet>
        </reportSets>
      </plugin>
    ...
    </plugins>
  </reporting>
```

# l10n:help #

  * Displays the list of goals and their configuration, by analysing Mojo annotations and code javadoc.
  * Full name: com.googlecode.l10n-maven-plugin:l10n-maven-plugin:1.8:help
  * Since version 1.5