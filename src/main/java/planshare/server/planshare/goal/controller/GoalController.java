package planshare.server.planshare.goal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import planshare.server.planshare.domain.Goal;
import planshare.server.planshare.goal.dto.GoalForm;
import planshare.server.planshare.goal.service.GoalService;
import planshare.server.planshare.user.dto.CustomUserDetailsVO;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/goal")
public class GoalController {

    private final GoalService goalService;

    @PostMapping("/create")
    public GoalForm create(@AuthenticationPrincipal CustomUserDetailsVO userDetailsVO, @RequestBody GoalForm goalForm){ //@AuthenticationPrincipal CustomUserDetailsVO cud

        System.out.println("controller : "+userDetailsVO+"\n"+goalForm);
        goalService.addGoal(userDetailsVO,goalForm);
        return goalForm;
    }

    // goal of member
    @GetMapping("/read_member")
    public List<Goal> listOfMember(@AuthenticationPrincipal CustomUserDetailsVO userDetailsVO){
        return goalService.findGoalsOfMember(userDetailsVO);
    }

    // goal of member and name
    @GetMapping("/read_member_name")
    public List<Goal> listOfMemberAndName(@AuthenticationPrincipal CustomUserDetailsVO userDetailsVO, @RequestParam String name){

        return goalService.findGoalsOfMemberAndName(userDetailsVO, name);
    }

    // goals
    @GetMapping("/read_alluser")
    public List<Goal> listOfGoals(@AuthenticationPrincipal CustomUserDetailsVO userDetailsVO){
        return goalService.findGoals();
    }

    // goal of name
    @GetMapping("/read_alluser_name")
    public List<Goal> listOfName(@AuthenticationPrincipal CustomUserDetailsVO userDetailsVO, @RequestParam String name){

        return goalService.findGoalsOfName(name);

    }




}