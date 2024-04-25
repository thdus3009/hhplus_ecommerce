package com.ecommerce.api;

import com.ecommerce.userPoint.api.controller.PointController;
import com.ecommerce.userPoint.api.dto.PointResponseDto;
import com.ecommerce.userPoint.domain.service.PointService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PointController.class)
public class PointControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PointService pointService;

    @Test
    void 포인트_충전_성공() throws Exception{
        Long userId = 1L;
        Long point = 5000L;
        PointResponseDto pointResponseDto = mkPointDto(userId, point);
        //PointResponseDto pointResponseDto = new PointResponseDto(1L, userId, point, ZonedDateTime.now(), null);
        // given > Mock 설정
        given(pointService.chargePoint(anyLong(), anyLong()))
                .willReturn(pointResponseDto);
        // when & then
        mockMvc.perform(patch("/points/" + userId + "/charge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "point" : 5000
                                }
                                """))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.userId").value(userId),
                        jsonPath("$.point").value(point)
                );

    }

    @Test
    @DisplayName("@Valid > @Positive 작동 확인")
    void 포인트_충전_실패_음수() throws Exception{
        Long userId = 1L;
        // when & then
        mockMvc.perform(patch("/points/" + userId + "/charge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "point" : -1000
                                }
                                """))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void 포인트_충전_실패_0일때() throws Exception{
        Long userId = 1L;
        // when & then
        mockMvc.perform(patch("/points/" + userId + "/charge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "point" : 0
                                }
                                """))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void 포인트_조회_성공() throws Exception{
        Long userId = 1L;
        Long point = 5000L;
        PointResponseDto pointResponseDto = mkPointDto(userId, point);
        // given
        given(pointService.getPoint(userId)).willReturn(pointResponseDto);

        // when & then
        mockMvc.perform(get("/points/" + userId))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.userId").value(userId),
                        jsonPath("$.point").value(point)
                );

    }

    PointResponseDto mkPointDto(Long userId, Long point){
        return PointResponseDto.builder()
                .id(1L)
                .userId(userId)
                .point(point)
                .createdAt(ZonedDateTime.now())
                .updatedAt(null)
                .build();
    }
}
