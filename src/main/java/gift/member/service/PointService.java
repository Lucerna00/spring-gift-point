package gift.member.service;

import gift.member.dto.PointDto;
import gift.member.entity.Member;
import gift.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PointService {

    private final MemberRepository memberRepository;

    @Autowired
    public PointService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public PointDto getPoint(Member member) {
       return new PointDto(member.getPoint());
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public PointDto addPoint(Member member, int point) {
        member.addPoint(point);
        memberRepository.save(member);
        return new PointDto(member.getPoint());
    }

    public PointDto subtractPoint(Member member, int point) {
        member.subtractPoint(point);
        memberRepository.save(member);
        return new PointDto(member.getPoint());
    }
}
