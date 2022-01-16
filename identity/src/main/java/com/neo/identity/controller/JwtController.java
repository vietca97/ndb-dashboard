package com.neo.identity.controller;//package com.neo.dashboard.controller;
//
//import com.neo.dashboard.models.AuthenticationResponseError;
//import com.neo.dashboard.security.MyUserDetailsService;
//import com.neo.dashboard.models.AuthenticationRequest;
//import com.neo.dashboard.models.AuthenticationResponse;
//import com.neo.dashboard.utils.JwtUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class JwtController {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtUtils jwtUtils;
//
//    @Autowired
//    private MyUserDetailsService userDetailsService;
//
//
//    @PostMapping("/authenticate")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
//            throws Exception {
//        try {
//            authenticationManager
//                    .authenticate(new UsernamePasswordAuthenticationToken
//                            (authenticationRequest.getUsername(), authenticationRequest.getPassword()));
//        } catch (BadCredentialsException e) {
//            //throw new Exception("Incorrect username and password", e);
//		return ResponseEntity.ok(new AuthenticationResponseError(500,"Incorrect username and password"));
//        }
//
//
//        final UserDetails userDetails = userDetailsService
//                .loadUserByUsername(authenticationRequest.getUsername());
//        final String jwt = jwtUtils.generateToken(userDetails);
//
//        return ResponseEntity.ok(new AuthenticationResponse(jwt,
//                userDetails.getUsername(),
//                authenticationRequest.getSmsSyntax(),
//                authenticationRequest.getSmsPackageCode(),
//                authenticationRequest.getSmsLocationCode(),
//                authenticationRequest.getSmsStore(),
//                authenticationRequest.getPhone(),
//                authenticationRequest.getStatus(),
//		200,
//"Success"
//                ));
//
//    }
//}
