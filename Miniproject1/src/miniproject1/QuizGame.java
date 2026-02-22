package miniproject1;
import javax.swing.*;
import java.util.*;
	public class QuizGame{
	static int reward = 0;
    static int fifty = 1;
    static int audience = 1;

    static String playerName;

    public static void main(String[] args) {

        // Take name using Swing
        playerName = JOptionPane.showInputDialog(null, "Enter your name:");

        if (playerName == null || playerName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name cannot be empty!");
            System.exit(0);
        }

        JOptionPane.showMessageDialog(null,
                "Welcome " + playerName + " 🎉\nLet's Play!");

        for (int q = 1; q <= 10; q++) {
            if (!askQuestion(q)) {
                JOptionPane.showMessageDialog(null,
                        "❌ Wrong Answer!\nGame Over\n\nThank you for playing!");
                break;
            }
        }

        JOptionPane.showMessageDialog(null,
                "🏆 Your Total Reward: ₹" + reward);
    }

    static boolean askQuestion(int q) {

        switch (q) {

            case 1:
                return questionBlock("Which company created Android?",
                        new String[]{"Apple", "Google", "Microsoft", "Samsung"}, 2);

            case 2:
                return questionBlock("What is the capital of India?",
                        new String[]{"Mumbai", "Delhi", "Chennai", "Kolkata"}, 2);

            case 3:
                return questionBlock("Which planet is known as the Red Planet?",
                        new String[]{"Earth", "Mars", "Jupiter", "Venus"}, 2);

            case 4:
                return questionBlock("Which symbol is used for comments in Java?",
                        new String[]{"//", "--", "++", "**"}, 1);

            case 5:
                return questionBlock("Which is the largest ocean in the world?",
                        new String[]{"Indian", "Atlantic", "Pacific", "Arctic"}, 3);

            case 6:
                return questionBlock("Which loop checks condition first?",
                        new String[]{"for", "do-while", "while", "if"}, 1);

            case 7:
                return questionBlock("How many Olympic gold medals has India won?",
                        new String[]{"5", "8", "10", "12"}, 3);

            case 8:
                return questionBlock("Which festival is known as the festival of lights?",
                        new String[]{"Holi", "Diwali", "Eid", "Christmas"}, 2);

            case 9:
                return questionBlock("Who gave the slogan 'Jai Jawan Jai Kisan'?",
                        new String[]{"Nehru", "Gandhi", "Subhash Bose", "Lal Bahadur Shastri"}, 4);

            case 10:
                return questionBlock("Which data type is used to store decimal values in Java?",
                        new String[]{"int", "float", "char", "boolean"}, 2);
        }

        return true;
    }

    static boolean questionBlock(String question, String[] options, int correct) {

        while (true) {

            StringBuilder msg = new StringBuilder();
            msg.append("Q").append((reward / 1000 + 1)).append(": ")
               .append(question).append("\n\n");

            for (int i = 0; i < options.length; i++) {
                msg.append((i + 1)).append(". ")
                   .append(options[i]).append("\n");
            }

            msg.append("5. Lifeline\n\n");
            msg.append("Enter your choice:");

            String input = JOptionPane.showInputDialog(msg.toString());

            if (input == null) {
                System.exit(0);
            }

            int ans;

            try {
                ans = Integer.parseInt(input);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please enter valid number!");
                continue;
            }

            if (ans == correct) {

                reward += 1000;

                JOptionPane.showMessageDialog(null,
                        "✅ Correct!\nYou won ₹1000\nTotal: ₹" + reward);

                return true;
            }

            else if (ans == 5) {

                if (!useLifeline(correct, options)) {
                    continue;
                }
                return true;
            }

            else {
                return false;
            }
        }
    }

    static boolean useLifeline(int correct, String[] options) {

        if (fifty == 0 && audience == 0) {

            JOptionPane.showMessageDialog(null,
                    "⚠️ All lifelines already used!");

            return false;
        }

        StringBuilder msg = new StringBuilder();
        msg.append("Choose Lifeline:\n");

        if (fifty == 1) msg.append("1. 50:50\n");
        if (audience == 1) msg.append("2. Audience Poll\n");

        String input = JOptionPane.showInputDialog(msg.toString());

        if (input == null) return false;

        int choice;

        try {
            choice = Integer.parseInt(input);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid choice!");
            return false;
        }

        // 50:50
        if (choice == 1) {

            if (fifty == 0) {
                JOptionPane.showMessageDialog(null,
                        "50:50 already used!");
                return false;
            }

            fifty = 0;

            StringBuilder fiftyMsg = new StringBuilder();
            fiftyMsg.append("50:50 Applied\n\n");

            int shown = 0;

            for (int i = 0; i < options.length; i++) {

                if (i + 1 == correct || shown == 0) {

                    if (i + 1 != correct) shown++;

                    fiftyMsg.append((i + 1))
                            .append(". ")
                            .append(options[i])
                            .append("\n");
                }
            }

            return finalAnswer(fiftyMsg.toString(), correct);
        }

        // Audience Poll
        else if (choice == 2) {

            if (audience == 0) {

                JOptionPane.showMessageDialog(null,
                        "Audience Poll already used!");

                return false;
            }

            audience = 0;

            StringBuilder poll = new StringBuilder();
            poll.append("Audience Poll\n\n");

            for (int i = 0; i < options.length; i++) {

                if (i + 1 == correct) {
                    poll.append((i + 1)).append(". 70%\n");
                } else {
                    poll.append((i + 1))
                        .append(". ")
                        .append((int) (Math.random() * 30))
                        .append("%\n");
                }
            }

            return finalAnswer(poll.toString(), correct);
        }

        else {
            JOptionPane.showMessageDialog(null, "Invalid choice!");
            return false;
        }
    }

    static boolean finalAnswer(String msg, int correct) {

        msg += "\nEnter your answer:";

        String input = JOptionPane.showInputDialog(msg);

        if (input == null) return false;

        int pick;

        try {
            pick = Integer.parseInt(input);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid number!");
            return false;
        }

        if (pick == correct) {

            reward += 1000;

            JOptionPane.showMessageDialog(null,
                    "✅ Correct!\nYou won ₹1000\nTotal: ₹" + reward);

            return true;
        }

        return false;
    }
}