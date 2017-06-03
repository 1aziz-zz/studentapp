import os
import re
import unittest
import xml.etree.ElementTree as ET

# ------------- Python script for database consistency -------------
# NOTE:  This application controles the database schema file available
#       in the current directory to see if it doesn't contain any SQL
#       commands which change or remove any tables or columns from the
#       existing database structure. There's however one exception:
#       empty or unused tables can be removed. Before the main app
#       gets built and tested by a CI tool (like Travis CI), this
#       script's unittest should be succesfully tested. So before every
#       deployment the new possible database schema must be consistent with
#       with the existing one (used by the existing release).

error_message = 'NO FILE FOUND'
old_hbm = os.getenv('OLD_HBM', error_message)
old_dbschema = os.getenv('OLD_DB_SCHEMA', error_message)
new_dbschema = os.getenv('NEW_DB_SCHEMA', error_message)


class DBFileControl:
    def __init__(self, file):
        self.file = file

    # The function below removes all SQL/DDL comments from a string
    def remove_comments(self, text):
        self.is_not_used()
        a = re.sub(r'/\*.+?\*/', "", str.join(" ", text.splitlines()))
        b = re.sub(r'--.+?\n', '', a)
        c = re.sub(r'#.+?\n', '', b)
        d = re.sub(r'/\*.+?\n', '', c)
        return d

    # This function returns the contents of a file (database schema file)
    def get_file_contents(self):
        self.is_not_used()
        file = open(self.file, 'r+')
        file_contents = file.read()
        file.close()
        return self.remove_comments(file_contents).upper()

    # This function returns names of all tables used by a database schema file
    def get_db_tables(self):
        self.is_not_used()
        word = "CREATE"
        text = self.get_file_contents()
        table_names = []
        for i, line in enumerate(text.split()):
            if word == line:
                next_word_check = text.split()[i + 1] == "TABLE"
                if next_word_check:
                    table_names.append(text.split()[i + 2])
        return table_names

    # The function below returns True if the method
    # remove_comments(get_file_contents()) does not contain any SQL
    # commands which can remove or change any existing table or column
    def is_schema_passed(self):
        self.is_not_used()
        forbidden_words = ["ALTER", "DROP", "DELETE"]
        for word in forbidden_words:
            text = self.remove_comments(self.get_file_contents())
            if word in text:
                return False
            else:
                return True

    def is_not_used(self):
        pass


class HBMFileControl:
    hbm_file = ""

    def __init__(self, hbm_file):
        self.hbm_file = hbm_file

    def get_all_tables(self):
        self.is_not_used()
        tree = ET.parse(self.hbm_file)
        root = tree.getroot()
        db_structure = []  # {}
        for class_tag in root.iter('class'):
            table = class_tag.get('table')
            # columns = []
            # for column_tag in class_tag.iter('column'):
            #     column = column_tag.get('name')
            #     columns.append(column)
            #     db_structure[table] = columns
            db_structure.append(table.upper())
        return db_structure

    def get_unused_db_tables(self):
        self.is_not_used()
        unused_old_db_tables = []  # {}
        dbfilecontrol = DBFileControl(old_dbschema)
        print('Tables in the old database schema: ', dbfilecontrol.get_db_tables())
        print('Tables in the old hibernate file: ', self.get_all_tables())
        for table_in_old_db in dbfilecontrol.get_db_tables():
            if table_in_old_db not in self.get_all_tables():
                unused_old_db_tables.append(table_in_old_db)
        return unused_old_db_tables

    def is_not_used(self):
        pass


class Test(unittest.TestCase, DBFileControl, HBMFileControl):
    def test_new_schema_file(self):
        if new_dbschema == error_message:
            self.fail(error_message)
        else:
            db_file_control = DBFileControl(new_dbschema)
            print('Tables in the new database schema: ', db_file_control.get_db_tables())
        self.assertTrue(db_file_control.is_schema_passed())

    def test_existing_unused_tables(self):
        if old_hbm == error_message and old_dbschema == error_message:
            self.fail(error_message)
        else:
            hbm_file_control = HBMFileControl(old_hbm)
            print('Unused tables: ', hbm_file_control.get_unused_db_tables())


if __name__ == '__main__':
    unittest.main()
