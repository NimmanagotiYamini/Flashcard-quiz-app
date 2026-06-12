import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Flashcard {
    String question;
    String answer;

    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}

public class FlashcardQuizApp extends JFrame {

    private ArrayList<Flashcard> flashcards = new ArrayList<>();
    private int currentIndex = 0;

    private JLabel questionLabel;
    private JLabel answerLabel;

    public FlashcardQuizApp() {

        setTitle("Flashcard Quiz App");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Sample Flashcards
        flashcards.add(new Flashcard("What is Java?", "A Programming Language"));
        flashcards.add(new Flashcard("What is OOP?", "Object Oriented Programming"));
        flashcards.add(new Flashcard("What is JVM?", "Java Virtual Machine"));

        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));

        answerLabel = new JLabel("", SwingConstants.CENTER);
        answerLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton showAnswerBtn = new JButton("Show Answer");
        JButton nextBtn = new JButton("Next");
        JButton prevBtn = new JButton("Previous");
        JButton addBtn = new JButton("Add");
        JButton editBtn = new JButton("Edit");
        JButton deleteBtn = new JButton("Delete");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(prevBtn);
        buttonPanel.add(showAnswerBtn);
        buttonPanel.add(nextBtn);

        JPanel managePanel = new JPanel();
        managePanel.add(addBtn);
        managePanel.add(editBtn);
        managePanel.add(deleteBtn);

        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridLayout(2,1));
        centerPanel.add(questionLabel);
        centerPanel.add(answerLabel);

        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(managePanel, BorderLayout.NORTH);

        displayFlashcard();

        // Show Answer
        showAnswerBtn.addActionListener(e -> {
            answerLabel.setText("Answer: " +
                    flashcards.get(currentIndex).answer);
        });

        // Next
        nextBtn.addActionListener(e -> {
            if (currentIndex < flashcards.size() - 1) {
                currentIndex++;
                displayFlashcard();
            }
        });

        // Previous
        prevBtn.addActionListener(e -> {
            if (currentIndex > 0) {
                currentIndex--;
                displayFlashcard();
            }
        });

        // Add Flashcard
        addBtn.addActionListener(e -> {
            String question = JOptionPane.showInputDialog("Enter Question:");
            String answer = JOptionPane.showInputDialog("Enter Answer:");

            if (question != null && answer != null &&
                    !question.isEmpty() && !answer.isEmpty()) {
                flashcards.add(new Flashcard(question, answer));
                currentIndex = flashcards.size() - 1;
                displayFlashcard();
            }
        });

        // Edit Flashcard
        editBtn.addActionListener(e -> {
            if (!flashcards.isEmpty()) {
                Flashcard card = flashcards.get(currentIndex);

                String newQuestion = JOptionPane.showInputDialog(
                        "Edit Question:", card.question);

                String newAnswer = JOptionPane.showInputDialog(
                        "Edit Answer:", card.answer);

                if (newQuestion != null && newAnswer != null) {
                    card.question = newQuestion;
                    card.answer = newAnswer;
                    displayFlashcard();
                }
            }
        });

        // Delete Flashcard
        deleteBtn.addActionListener(e -> {
            if (!flashcards.isEmpty()) {
                flashcards.remove(currentIndex);

                if (currentIndex >= flashcards.size()) {
                    currentIndex = flashcards.size() - 1;
                }

                if (flashcards.isEmpty()) {
                    questionLabel.setText("No Flashcards Available");
                    answerLabel.setText("");
                } else {
                    displayFlashcard();
                }
            }
        });
    }

    private void displayFlashcard() {
        questionLabel.setText("Question: " +
                flashcards.get(currentIndex).question);
        answerLabel.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FlashcardQuizApp().setVisible(true);
        });
    }
}