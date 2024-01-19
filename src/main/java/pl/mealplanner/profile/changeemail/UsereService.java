package pl.mealplanner.profile.changeemail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsereService {
    @Autowired
    private UsereRepository usereRepository;

    public Usere findByEmail(String email) {
        return usereRepository.findByEmail(email);
    }

    public Usere saveUser(Usere user) {
        return usereRepository.save(user);
    }

    public void deleteUser(Usere user) {
        usereRepository.delete(user);
    }

    public Usere getUserByUsername(String username) {
        return usereRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


}
