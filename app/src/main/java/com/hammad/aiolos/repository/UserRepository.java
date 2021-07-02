package com.hammad.aiolos.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.hammad.aiolos.data.UserDAO;
import com.hammad.aiolos.data.UserDataBase;
import com.hammad.aiolos.model.User;

import java.util.concurrent.ExecutionException;

public class UserRepository {
    private UserDAO userDAO;


    public UserRepository(Application application){
        UserDataBase userDataBase = UserDataBase.getDatabase(application);
        userDAO = userDataBase.getUserDao();
    }

    public User getUser(String email) throws ExecutionException, InterruptedException {
        return new GetUsersAsyncTask(email).execute().get();
    }

    private class GetUsersAsyncTask extends AsyncTask<Void, Void,User>
    {
        String email;
        public GetUsersAsyncTask(String email) {
            this.email = email;
        }

        @Override
        protected User doInBackground(Void... url) {
            return userDAO.getUser(email);
        }

    }


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

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }
}
