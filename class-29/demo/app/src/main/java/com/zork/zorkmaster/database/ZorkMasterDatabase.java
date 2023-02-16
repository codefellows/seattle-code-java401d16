package com.zork.zorkmaster.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.zork.zorkmaster.dao.SuperPetDao;
import com.zork.zorkmaster.model.SuperPet;


// TODO Step: 4-6 Enable converters
@TypeConverters({ZorkMasterDatabaseConverters.class})
//TODO Step: 4-5 Setup the database and make it abstract
@Database(entities = {SuperPet.class}, version = 1) // if we update the version, it will delete the db!
public abstract class ZorkMasterDatabase extends RoomDatabase {
    // TODO Step: 4-6 add your DAO's!
    public abstract SuperPetDao superPetDao();
}
