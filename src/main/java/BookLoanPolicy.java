// loan policy for books
public class BookLoanPolicy implements LoanPolicy {
    @Override 
    public int calculateLoanDuration(Member member){
        if (member.getMemberType() == MemberType.STUDENT){
            return 28;
        }
        return 14;
    }
} 
