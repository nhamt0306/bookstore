package edu.hcmute.bookstore.security.principal;

import edu.hcmute.bookstore.model.UserEntity;
import edu.hcmute.bookstore.repository.UserRepository;
import edu.hcmute.bookstore.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Tài khoản không tồn tại!"));
        return UserPrinciple.build(user);
    }

    //Lấy ra User hiện tại
    //HAM LAY RA USER HIEN TAI DE THUC HIEN THAO TAC VOI DB
    public UserEntity getCurrentUser(){
        Optional<UserEntity> user;
        String userName;
        //Lay 1 object principal trong SecurityContexHolder
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //So sanh obj voi Userdetails neu ma dung thi gan userName = principal.getUsername();
        if(principle instanceof UserDetails){
            userName = ((UserDetails) principle).getUsername();
        } else {
            //neu khong phai user hien tai thi userName = principal.toString();
            userName = principle.toString();
        }
        //kiem tra neu userName ton tai trong DB thi gan user = ham tim kiem trong DB theo userName do
        if(userRepository.existsByUsername(userName)){
            user = userRepository.findByUsername(userName);
        } else {
            //Neu chua ton tai thi tra ve 1 the hien cua lop User thong qua Optional.of
            user = Optional.of(new UserEntity());
            //set cho no 1 cai ten user an danh Day la truong hop ma tuong tac qua dang nhap kieu FB hay GG
            user.get().setUsername("Anonymous");
        }
        return user.get();
    }
}
