package kz.iitu.kettik.authentication.services;

import kz.iitu.kettik.authentication.entities.Avatar;
import kz.iitu.kettik.authentication.repositories.AvatarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvatarService {
    private final AvatarRepository repository;
    private final UserService userService;

    public Avatar add(String email, String path){
        Avatar newFile = repository.save(Avatar.builder()
                .filePath(path)
                .user(userService.findByEmail(email))
                .build());
        return newFile;
    }

//    public void delete(Integer reviewId) {
//        Avatar Avatar = repository.findById(reviewId).orElseThrow(() -> {
//            return new EntityNotFoundException("Фото", reviewId);
//        });
//        repository.delete(Avatar);
//    }
}
