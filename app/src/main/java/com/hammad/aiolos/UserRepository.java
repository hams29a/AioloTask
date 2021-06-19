package com.hammad.aiolos;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;

import com.hammad.aiolos.data.UserDAO;
import com.hammad.aiolos.data.UserDataBase;
import com.hammad.aiolos.model.User;

import java.util.List;

public class UserRepository {
    private UserDAO userDAO;
    private User user;
    private UserDataBase userDataBase;


    public UserRepository(UserDataBase userDataBase){
            userDAO = userDataBase.getUserDao();
    }

    public User getUser(String email, String password){
//        userDAO.getUser(email);
        if(email.equals("abc@gmail.com") && password.equals("abc123"))
            return new User("abc@gmail.com", "abc123", "abc");
        else
            return new User("", "", "");
    }

    private MutableLiveData<User> searchResults =
            new MutableLiveData<>();

    public void asyncFinished(User user){
        searchResults.setValue(user);
    }

//    public User getUser(String email){
//        return new getUserAsyncTask(userDAO, email).execute();
//    }

    public void insert(User user){
        new insertAsyncTask(userDAO).execute(user);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void>{
        private UserDAO taskDao;

        insertAsyncTask(UserDAO userDAO){
            taskDao = userDAO;
        }

        @Override
        protected Void doInBackground(User... users) {
            taskDao.insert(users[0]);

            return null;
        }

    }

//    private static class getUserAsyncTask1 extends
//            AsyncTask<String, Void, User> {
//
//        private UserDAO userDAO;
//        private UserRepository userRepository = null;
//
//        getUserAsyncTask(UserDAO dao) {
//            userDAO = dao;
//        }
//
//        @Override
//        protected User doInBackground(final String... params) {
//            return userDAO.getUser(params[0]);
//        }
//
//        @Override
//        protected void onPostExecute(User result) {
//            userRepository.asyncFinished(result);
//        }
//    }

    private static class getUserAsyncTask extends AsyncTask<Void, Void, User> {

        //Prevent leak
        private String email;
        UserDAO userDAO;

//        UserDataBase userDataBase = UserDataBase.getDatabase();

        public  getUserAsyncTask(UserDAO userDAO, String email) {
            this.userDAO = userDAO;
            this.email = email;
        }

        @Override
        protected User doInBackground(Void... params) {
//            AgentDao agentDao = MyApp.DatabaseSetup.getDatabase().agentDao();
//            userDAO = userDataBase.getUserDao();
            return userDAO.getUser(email);
        }


//        @Override
//        protected void onPostExecute(User user) {
//
//        }
    }


//    private static class getUserAsyncTask extends AsyncTask<User, Void, Void>{
//        private UserDAO taskDao;
//
//        getUserAsyncTask(UserDAO userDAO){
//            taskDao = userDAO;
//        }
//
//        @Override
//        protected Void doInBackground(User... users) {
//            return null;
//        }
//
//        public User execute(String email, String password) {
//            return taskDao.getUser(email, password);
//        }
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//
//        }
//
//    }
}
