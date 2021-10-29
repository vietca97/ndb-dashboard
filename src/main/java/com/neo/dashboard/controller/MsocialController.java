package com.neo.dashboard.controller;

import com.example.springsecurityjwt.models.*;
import com.neo.dashboard.models.*;
import neo.dashboard.models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MsocialController {

    @PostMapping("/validate")
    public MsocialResponse validate(@RequestBody MsocialRequest msocialRequest) {
        if ("ST70".equals(msocialRequest.getSmsSyntax())
                && "PM2".equals(msocialRequest.getSmsLocationCode())
                && "INTERNET".equals(msocialRequest.getSmsPackageCode())
                && "NEO".equals(msocialRequest.getSmsStore())
                && "0123456789".equals(msocialRequest.getPhone())
        ) {
            return new MsocialResponse(msocialRequest, "Success validate !", 200);
        }
        return new MsocialResponse(msocialRequest, "Error validate !", 500);
    }

    @PostMapping("/check/{status}/{phone}/{smsPackageCode}/{smsSyntax}")
    public InfoSocialResponse check(
            @PathVariable(required = false,name="status") Integer status,
//            @RequestParam(required=false,name="status") Integer status,

//            @RequestParam Integer status,
//                                 @RequestParam(required=false,name="phone") String phone,
            @PathVariable(required = false,name="phone") String phone,
//                                 @RequestParam(required=false,name="smsPackageCode") String smsPackageCode,
            @PathVariable(required = false,name="smsPackageCode") String smsPackageCode,
//            @RequestParam(required=false,name="smsSyntax") String smsSyntax
            @PathVariable(required = false,name="smsSyntax") String smsSyntax   ) {
        if (status == 0
        ) {
            return new InfoSocialResponse(new ResponseDTO(phone,status,smsPackageCode,smsSyntax), "Success check !", 200);
        }
        return new InfoSocialResponse(new ResponseDTO(phone,status,smsPackageCode,smsSyntax), "Error check !", 500);
    }

    @PostMapping("/registry")
    public ResultMessage registry(
            @RequestHeader("smsSyntax") String smsSyntax,
            @RequestHeader("phone") String phone,
            @RequestHeader("smsPackageCode") String smsPackageCode
    )
    {
        return new ResultMessage(new Data(phone,smsPackageCode,smsSyntax),"Registry success !", "200");
    }

    @GetMapping("/error")
    public ResponseEntity<?> error()
    {
            return ResponseEntity.ok(new ResponseError(500,"Info msocial invalid !"));
    }
}
