# JSON to CSV Flattener

This Maven project reads multiple JSON files from the `jsons/` directory and flattens them into a single `output.csv` file.

## Features

- Supports deeply nested objects and arrays
- Uses `flat-fields.properties` to:
  - Specify which fields to include
  - Provide custom headers
  - Control CSV column order

## Important Notes

- **CSV column order follows the order in `flat-fields.properties`.**
- To preserve the order in the JSON structure, list the fields in the same order in the `.properties` file.
- Fields not mentioned in `flat-fields.properties` are ignored.
- Use wildcards like `[*]` for arrays:
  ```properties
  address.locations[*].coords[*]=Y:Coord
  ```

## Running the Program

1. Place your `.json` files in the `jsons/` folder.
2. Update `flat-fields.properties` as needed.
3. Run the program:

```
mvn compile exec:java -Dexec.mainClass="com.example.Main"
```

## Output

- Generates `output.csv` with:
  - 1 header row (customized via `.properties`)
  - 1 data row per JSON file

Enjoy!
