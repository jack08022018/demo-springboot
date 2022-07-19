//package com.demo.service;
//
//import com.demo.entity.MMusicEntity;
//import com.demo.repository.realDb.MMusicRepository;
//import com.demo.service.impl.ApiServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
//import static org.mockito.ArgumentMatchers.any;
//import org.mockito.Mock;
//import org.mockito.MockedStatic;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class BookServiceTest {
//
//    @InjectMocks
//    ApiServiceImpl apiService;
//
//    @Mock
//    MMusicRepository mMusicRepository;
//
//    @Test
//    void whenGetAll_shouldReturnList() {
//        // 1. create mock data
//        List<MMusicEntity> mockData = new ArrayList<>();
//        for(int i = 1; i <= 5; i++) {
//            MMusicEntity e = MMusicEntity.builder()
//                    .id(i)
//                    .name("Name " + i)
//                    .build();
//            mockData.add(e);
//        }
//
//        // 2. define behavior of Repository
//        when(mMusicRepository.getMusicByName("Name")).thenReturn(mockData);
//
//        // 3. call service method
//        List<MMusicEntity> actualData = apiService.getMusicByName("Name");
//
//        // 4. assert the result
//        assertThat(actualData.size()).isEqualTo(mockData.size());
//
//        // 4.1 ensure repository is called
//        verify(mMusicRepository).getMusicByName("Name");
//    }
//
//    @Test
//    void whenGetInvalidOne_shouldThrowException() {
//        Integer invalidBookId = 7;
//        when(mMusicRepository.findById(any(Integer.class)))
//                .thenReturn(Optional.ofNullable(null));
//
//        assertThatThrownBy(() -> apiService.getOne(invalidBookId))
//                .isInstanceOf(FileNotFoundException.class);
//
//        verify(mMusicRepository).findById(any(Integer.class));
//    }
//
//}
