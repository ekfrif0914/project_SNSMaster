package com.codemaster.project_snsmaster.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FollowVO {

    private String userid;//팔로우 하는 주체
    private String followid;//팔로우 당하는 사람
}
