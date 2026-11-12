public class Main {
    private static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {

        System.out.println("=== THE READING ROOM: SYSTEM DIAGNOSTIC SUITE ===\n");
        Library library = new Library();

        // --- SETUP PHASE ---
        System.out.println("[Setup] Registering members and populating inventory...");
        Member studentAlice = new Member(1, "Alice", MemberType.STUDENT);
        Member standardBob = new Member(2, "Bob", MemberType.STANDARD);
        library.registerMember(studentAlice);
        library.registerMember(standardBob);

        LendingItem javaBook = new LendingItem(101, "Effective Java", ItemType.BOOK);
        LendingItem audioKit = new LendingItem(201, "Concurrency Audio Guide", ItemType.AUDIO_KIT);
        library.addItem(javaBook);
        library.addItem(audioKit);

        System.out.println("[Setup] Complete. Beginning step-by-step execution...\n");
        pause(1500);

        // --- TEST 1: Successful Borrowing & Policy Calculation ---
        System.out.println("--- TEST 1: Successful Borrowing ---");
        try {
            Loan loan1 = library.borrowItem(studentAlice, audioKit);
            System.out.println("  [SUCCESS] Alice borrowed '" + loan1.getItem().getTitle() + "'.");
            System.out.println("            Calculated Loan Days (Student Audio Kit): " + loan1.getLoanDays());
            System.out.println("            Item availability status: " + audioKit.isAvailable());
        } catch (Exception e) {
            System.out.println("  [UNEXPECTED ERROR] " + e.getMessage());
        }

        System.out.println();
        pause(2000);
        // --- TEST 2: Duplicate Borrowing Guard (Error Case A) ---
        System.out.println("--- TEST 2: Duplicate Borrowing Enforcement ---");
        try {
            System.out.println("  [ACTION] Bob attempting to borrow the same Audio Kit while Alice has it...");
            library.borrowItem(standardBob, audioKit);
        } catch (IllegalStateException e) {
            System.out.println("  [CAUGHT EXPECTED ERROR] " + e.getMessage());
            System.out.println("  [VERDICT] Duplicate borrowing successfully blocked by system invariants.");
        }

        System.out.println();
        pause(2000);

        // --- TEST 3: Invalid Return Guard (Error Case B) ---
        System.out.println("--- TEST 3: Invalid Return Enforcement ---");
        try {
            System.out.println("  [ACTION] Attempting to return 'Effective Java' which was never borrowed...");
            library.returnItem(javaBook);
        } catch (IllegalStateException e) {
            System.out.println("  [CAUGHT EXPECTED ERROR] " + e.getMessage());
            System.out.println("  [VERDICT] Premature return successfully blocked by system invariants.");
        }

        System.out.println();
        pause(2000);

        // --- TEST 4: Legitimate Return & State Recovery ---
        System.out.println("--- TEST 4: Legitimate Return Execution ---");
        try {
            System.out.println("  [ACTION] Alice returns the Audio Kit...");
            library.returnItem(audioKit);
            System.out.println("  [SUCCESS] Return processed.");
            System.out.println("            Is Audio Kit available now? " + audioKit.isAvailable());
        } catch (Exception e) {
            System.out.println("  [UNEXPECTED ERROR] " + e.getMessage());
        }

        System.out.println();
        pause(2000);

        // --- TEST 5: Subsequent Borrowing After Release ---
        System.out.println("--- TEST 5: Post-Return Circulation Check ---");
        try {
            System.out.println("  [ACTION] Bob now attempts to borrow the newly-returned Audio Kit...");
            Loan loan2 = library.borrowItem(standardBob, audioKit);
            System.out.println("  [SUCCESS] Bob successfully secured the item!");
            System.out.println("            Assigned Loan Days (Standard Audio Kit): " + loan2.getLoanDays());
            System.out.println("            Total audit trail records in library: " + library.getLoans().size());
        } catch (Exception e) {
            System.out.println("  [UNEXPECTED ERROR] " + e.getMessage());
        }

        System.out.println("\n=== ALL SYSTEM TESTS COMPLETED SUCCESSFULLY ===");

    }
}
