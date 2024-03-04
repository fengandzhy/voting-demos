package org.frank.vote.controllers;

import com.sun.istack.NotNull;
import org.frank.vote.entities.Candidate;
import org.frank.vote.entities.Recorder;
import org.frank.vote.entities.User;
import org.frank.vote.services.CandidateService;
import org.frank.vote.services.RecorderService;
import org.frank.vote.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class VoteController {

    private CandidateService candidateService;
    private RecorderService recorderService;
    private UserService userService;
    
    public VoteController(@NotNull CandidateService candidateService,
                          @NotNull RecorderService recorderService,
                          @NotNull UserService userService){
        this.candidateService = candidateService;
        this.recorderService = recorderService;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('user')")
    @RequestMapping(value="/vote",method={RequestMethod.POST})
    public String vote(@NotNull String candidateSelection, @NotNull Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        Recorder recorder = new Recorder();
        recorder.setUsername(user.getUsername());
        recorder.setCandidateName(candidateSelection);
        recorder.setVoteTime(LocalDateTime.now());
        recorderService.saveNewVoteRecord(recorder);
        candidateService.incrementVote(candidateSelection);
        userService.updateUserVoteStatus(user.getUsername());
        model.addAttribute("candidateName", candidateSelection);
        return "/pages/success";
    }

    @RequestMapping(value="/view.html",method={RequestMethod.GET})
    public String view(@NotNull Model model){
        List<Candidate> candidateList = candidateService.findAll();
        model.addAttribute("candidates", candidateList);
        return "/pages/view"; 
    }
}
