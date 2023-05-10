package edu.uncc.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import edu.uncc.finalexam.auth.LoginFragment;
import edu.uncc.finalexam.auth.RegisterFragment;
import edu.uncc.finalexam.models.News;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginFragmentListener,
        RegisterFragment.RegisterFragmentListener, NewsFragment.NewsFragmentListener, NewsDetailsFragment.DetailsInterface, MyListsFragment.MyListInterface, AddToListFragment.AddToListInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentView, new LoginFragment())
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentView, new NewsFragment())
                    .commit();
        }
    }

    @Override
    public void gotoRegister() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentView, new RegisterFragment())
                .commit();
    }

    @Override
    public void gotoLogin() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentView, new LoginFragment())
                .commit();
    }

    @Override
    public void successGotoNewsFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentView, new NewsFragment())
                .commit();
    }

    @Override
    public void logout() {
        FirebaseAuth.getInstance().signOut();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentView, new LoginFragment())
                .commit();
    }

    @Override
    public void goToDetails(News item) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentView,  NewsDetailsFragment.newInstance(item))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToMyList() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentView,new MyListsFragment())
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void goToAddList(News item) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentView,  AddToListFragment.newInstance(item))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToListView() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentView, new ListDetailsFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void back() {
        getSupportFragmentManager().popBackStack();
    }
}