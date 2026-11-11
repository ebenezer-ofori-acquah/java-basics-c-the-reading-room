// loan policy for audio kits
public class AudioKitLoanPolicy implements LoanPolicy {
    @Override
    public int calculateLoanDays(Member member) {
        if (member.getMemberType() == MemberType.STUDENT) {
            return 14;
        }
        return 7;
    }
}
