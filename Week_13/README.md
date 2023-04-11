 # Mobile Application Development
## Local Data Storage
### Author: Viraj Ranaware

## Important Classes:
### SQLite Classes:
**SQLiteOpenHelper:**
- A helper class to manage database creation and version management.
- This class contains a useful set of APIs for managing the database.
- **onCreate**(SQLiteDatabase db): Called when the database is created for the first time (i.e if database does not exist).This is where the creation of tables and the initial population of the tables should happen.
- **onUpgrade**(SQLiteDatabase db, int oldVersion, int newVersion): Called when the database needs to be upgraded.(DB version passed is heigher than the current version)
- SQLiteOpenHelper instance will be used to get database instance:
 - **getWritableDatabase()**: to perfrom read/write operations
 - **getReadableDatabase()**: to perfrom read only operations
- To use this class, create a DB Helper class which extends SQLiteOpenHelper, override onCreate() and onUpgrade() methods
<img width="722" alt="image" src="https://user-images.githubusercontent.com/112779376/231275733-40cb9cef-eb88-4653-a960-4eb28a45a601.png">

**SQLiteDatabase:**
- Exposes methods to manage a SQLite database.
- SQLiteDatabase has methods to create, delete, execute SQL commands, and perform other common database management tasks.

### Custom Classes:
**Table Classess:**
- This class corresponds to a table in the database.
- It is used to perform the SQL operations required to. create and update a specific table. Example below:

<img width="715" alt="image" src="https://user-images.githubusercontent.com/112779376/231276310-a17df4e5-31cc-49d2-8a77-d7fa09651216.png">

**Data Object class:**
- This class will be created to represent the data model.
- Its object will represent a row of the table.
- Class below represents a model for a Notes table.

<img width="535" alt="image" src="https://user-images.githubusercontent.com/112779376/231276767-4bb1e058-5509-4f7c-acd9-2e8f8e1b82a2.png">

**Data Access Object (DAO):**
- The DAO class is used to represent the data access layer.
- It is used to perform SQL operations required for accessig and manipulating data in each table.
- E.g DAO class for our Notes table:

<img width="325" alt="image" src="https://user-images.githubusercontent.com/112779376/231277988-6481448b-fc22-4dd9-858c-8dddeee231ec.png">
<img width="844" alt="image" src="https://user-images.githubusercontent.com/112779376/231277688-74712705-8b7e-4d2d-9030-e348a092d68c.png">
<img width="863" alt="image" src="https://user-images.githubusercontent.com/112779376/231277855-86109726-003e-4717-8b49-2615603829b1.png">

**Getting Object from a Cursor**

<img width="569" alt="image" src="https://user-images.githubusercontent.com/112779376/231280476-43ede0e1-79bf-4ec8-a4b4-0722c8af9e9a.png">

**Data Manager**
- This is a wrapper class that connects the designed classes.
- It is used to 
 - create or update the database.
 - maintain the database instance
 - call different access classes for the different tables.
 - close the DB connection.

<img width="504" alt="image" src="https://user-images.githubusercontent.com/112779376/231279177-c2ac3b35-1a40-46fd-a881-cbf4d6ac3a9e.png">

### Inspecting the SQLite DB:
- The database application is stored in a file in the emulator folder:  
  "/data/data/<package_name>/databases/<database_name>"
- This file can be downloaded and viewed in DB browser for SQLite: [SQLite Broswer](https://sqlitebrowser.org/)
  
```

```


