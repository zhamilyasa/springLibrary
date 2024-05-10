package org.example.library.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.library.dtos.UserActionRequest;
import org.example.library.models.entity.UserAction;
import org.example.library.models.enums.ActionType;
import org.example.library.service.UserActionService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v4/action")
@RequiredArgsConstructor
public class UserActionController {
    private final UserActionService userActionService;

    @PostMapping("/create")
    @Operation(summary = "createAction")
    public void createAction(@RequestBody UserActionRequest userActionRequest) {
        userActionService.createAction(userActionRequest);
    }

}
