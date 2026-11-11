/**
 * Represents a reading-room member using an ID, name, and member type.
 * Provides getters for these three member details.
 */
public class Member {
    private int memberId;
    private String name;
    private MemberType memberType;

    public Member(int memberId, String name, MemberType memberType){
        this.memberId = memberId;
        this.name = name;
        this.memberType = memberType;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public MemberType getMemberType() {
        return memberType;
    }
}
