More exotic resources can be validated against any regular expression with the following configuration:

| **Parameter name** | **Type** | **Since** | **Description** |
|:-------------------|:---------|:----------|:----------------|
| **name**           | `String` | 1.3       | Friendly name for the pattern to use in error reporting |
| **regex**          | `String` | 1.3       | Regex to use for validation |
| **keys**           | `String[]` | 1.3       | List of keys to validate with the pattern |

```
<customPatterns>
  <customPattern>
    <name>CSV list pattern</name>
    <regex>^[A-Z]+(,[A-Z]+)+$</regex>
    <keys>
      <key>list.country</key>
      <key>list.language</key>
      <key>list.city</key>
    </keys>
  </customPattern>
  <customPattern>
   ...
  </customPattern>
</customPatterns>
```

Here are some sample Regex:

| **Pattern name** | **regex** |
|:-----------------|:----------|
| E-mail           | ^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$_|
| List of country codes | ^[A-Z]{2}(,[A-Z]{2})+$ |
| List of error numbers | ^[0-9]+(:[0-9])+$ |