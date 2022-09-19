package br.com.studies.spring.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.studies.spring.entities.Users;
import br.com.studies.spring.repositories.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
	private UserRepository repository;

    private List<Users> usersL = new ArrayList<>();
	private String username;
	private String password;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        setUsersL(repository.findAll());
        for (Users user : this.getUsersL()) {
            if (user.getUsername().equals(username)) {
                this.username = user.getUsername();
                this.password = user.getPassword();
            }
        }
        return new User(this.username, this.password, new ArrayList<>());
    }

    public List<Users> getUsersL() {
		return usersL;
	}

    private void setUsersL(List<Users> users) {
        this.usersL = users;
    }
    
}
