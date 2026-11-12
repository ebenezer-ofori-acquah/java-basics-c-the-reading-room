public class BookLoanPolicy implements LoanPolicy {
    @Override
    public int calculateLoanDays(Member member) {
        if (member.getMemberType() == MemberType.STUDENT) {
            return 28;
        }
        return 14;
    }
}
