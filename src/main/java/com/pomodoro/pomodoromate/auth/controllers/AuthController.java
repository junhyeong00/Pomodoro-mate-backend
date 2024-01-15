package com.pomodoro.pomodoromate.auth.controllers;

import com.pomodoro.pomodoromate.auth.applications.GuestLoginService;
import com.pomodoro.pomodoromate.auth.dtos.LoginResponseDto;
import com.pomodoro.pomodoromate.auth.dtos.TokenDto;
import com.pomodoro.pomodoromate.common.utils.HttpUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final GuestLoginService guestLoginService;
    private final HttpUtil httpUtil;

    public AuthController(GuestLoginService guestLoginService,
                          HttpUtil httpUtil) {
        this.guestLoginService = guestLoginService;
        this.httpUtil = httpUtil;
    }

    @PostMapping("guest")
    public ResponseEntity<LoginResponseDto> guestLogin(
            HttpServletResponse response
    ) {
        TokenDto token = guestLoginService.login();

        ResponseCookie cookie = httpUtil.generateHttpOnlyCookie("refreshToken", token.refreshToken());

        httpUtil.addCookie(cookie, response);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new LoginResponseDto(token.accessToken()));
    }
}