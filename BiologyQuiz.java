/**
 * A quiz with questions on IB SL Biology.
 *
 * modified     20220823
 * date         20220823
 * @filename    BiologyQuiz.java
 * @author		CaseyGao
 * @version     1.0
 * @see         ICS3U
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

public class BiologyQuiz extends JFrame {
	int currentPage = 0;
	final int NUMBER_OF_QUESTION = 10;
	String[] questionBank1 = new String[NUMBER_OF_QUESTION + 1]; 
	String[] questionBank2 = new String[NUMBER_OF_QUESTION + 1]; // stores the second line of the question (if needed)
	String[] option1Bank = new String[NUMBER_OF_QUESTION + 1];
	String[] option2Bank = new String[NUMBER_OF_QUESTION + 1];
	String[] option3Bank = new String[NUMBER_OF_QUESTION + 1];
	String[] option4Bank = new String[NUMBER_OF_QUESTION + 1];
 	String[] answerBank = new String[NUMBER_OF_QUESTION + 1];
	String[] userAnswer = new String[NUMBER_OF_QUESTION + 1];	
	
	private JPanel contentPane;
	private JTextField txtAnswer;
	private JLabel lblQuestion1;
	private JLabel lblQuestion2;
	private JLabel lblAnswer1;
	private JLabel lblAnswer2;
	private JLabel lblAnswer3;
	private JLabel lblAnswer4;
	private JLabel lblProgress;
	private JLabel lblError;
	private JLabel lblAnswer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BiologyQuiz frame = new BiologyQuiz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public BiologyQuiz() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 902, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBiologyQuiz = new JLabel("Biology Quiz!");
		lblBiologyQuiz.setHorizontalAlignment(SwingConstants.CENTER);
		lblBiologyQuiz.setFont(new Font("Segoe UI", Font.PLAIN, 36));
		lblBiologyQuiz.setBounds(10, 11, 866, 54);
		contentPane.add(lblBiologyQuiz);
		
		lblQuestion1 = new JLabel("Answer each multiple choice question by filling the box at the bottom with a letter (A-D).\r\n");
		lblQuestion1.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuestion1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblQuestion1.setBounds(10, 76, 866, 39);
		contentPane.add(lblQuestion1);
		
		lblQuestion2 = new JLabel("Then click next to move onto the next question.\r\n");
		lblQuestion2.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuestion2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblQuestion2.setBounds(10, 104, 866, 39);
		contentPane.add(lblQuestion2);
		
		lblAnswer1 = new JLabel("You may click previous to go back anytime before your results are displayed.\r\n");
		lblAnswer1.setHorizontalAlignment(SwingConstants.LEFT);
		lblAnswer1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblAnswer1.setBounds(35, 153, 841, 39);
		contentPane.add(lblAnswer1);
		
		lblAnswer2 = new JLabel("You may leave a question blank and come back to it later.");
		lblAnswer2.setHorizontalAlignment(SwingConstants.LEFT);
		lblAnswer2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblAnswer2.setBounds(35, 226, 841, 39);
		contentPane.add(lblAnswer2);
		
		lblAnswer4 = new JLabel("Click next to start the quiz.");
		lblAnswer4.setHorizontalAlignment(SwingConstants.LEFT);
		lblAnswer4.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblAnswer4.setBounds(35, 372, 841, 39);
		contentPane.add(lblAnswer4);
		
		lblAnswer3 = new JLabel("Your results will be displayed after the \"Check your Answers\" page.\r\n");
		lblAnswer3.setHorizontalAlignment(SwingConstants.LEFT);
		lblAnswer3.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblAnswer3.setBounds(35, 299, 841, 39);
		contentPane.add(lblAnswer3);
		
		lblProgress = new JLabel("Instructions");
		lblProgress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProgress.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblProgress.setBounds(638, 469, 223, 39);
		contentPane.add(lblProgress);
		
		lblAnswer = new JLabel("");
		lblAnswer.setHorizontalAlignment(SwingConstants.LEFT);
		lblAnswer.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblAnswer.setBounds(35, 461, 86, 39);
		contentPane.add(lblAnswer);
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setHorizontalAlignment(SwingConstants.LEFT);
		lblError.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblError.setBounds(35, 430, 843, 39);
		contentPane.add(lblError);
				
		txtAnswer = new JTextField("");
		txtAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		txtAnswer.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		txtAnswer.setBounds(109, 461, 54, 39);
		contentPane.add(txtAnswer);
		txtAnswer.setColumns(10);
		txtAnswer.setVisible(false);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentPage != 0 && currentPage != 11 && currentPage !=12) {
					if (checkAnswer()){
						currentPage = currentPage + 1;
						updateText();
					}
					else if (!checkAnswer()) {
						lblError.setText("Error: The answer you submitted is invalid. Enter only the letter corresponding to your answer.");
					}
				}
				else if (currentPage == 0 || currentPage == 11) {
					currentPage = currentPage + 1;
					updateText();
				}
				else if (currentPage == 12) {
					  System.exit(0); // https://stackoverflow.com/questions/22286695/create-an-exit-button-in-java#:~:text=If%20you%20want%20a%20JButton,new%20JButton(%22Close%22)%3B
				}
			}
		});
		btnNext.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNext.setBounds(352, 463, 108, 35);
		contentPane.add(btnNext);
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentPage != 0 && currentPage != 11 && currentPage != 12) {
					if (checkAnswer()) {
						currentPage = currentPage - 1;
						updateText();
					}
					else if (!checkAnswer()) {
						lblError.setText("Error: The answer you submitted is invalid. Enter only the letter corresponding to your answer.");
					}
				}
				else if (currentPage == 11) {
					currentPage = currentPage - 1;
					updateText();
				}
				else if (currentPage == 12) {
					currentPage = 0;
					userAnswer = new String[NUMBER_OF_QUESTION + 1];	
					updateText();
				}				
			}
		});
		btnPrevious.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnPrevious.setBounds(192, 463, 133, 35);
		contentPane.add(btnPrevious);		
	}

	private void updateText() {
		questionBank1[0] = "Answer each multiple choice question by filling the box at the bottom with a letter (A-D).";
		questionBank2[0] = "Then click next to move onto the next question.";
		option1Bank[0] = "You may click previous to go back anytime before your results are displayed.";
		option2Bank[0] = "Your progress is displayed in the bottom right corner.";
		option3Bank[0] = "Your results will be displayed once you click next on the last question.";
		option4Bank[0] = "Click next to start the quiz.";
				
		questionBank1[1] = "Why is sweat a good coolant for the body?";
		option1Bank[1] = "The arterioles that transfer water to sweat move closer to the skin surface when it is hot";
		option2Bank[1] = "Breaking H bonds between water molecules in sweat requires energy from body heat.";
		option3Bank[1] = "Sweat contains minerals such as sodium chloride.";
		option4Bank[1] = "Sweat is non-polar.";
		answerBank[1] = "B";
		
		questionBank1[2] = "Which is an effect of protein denaturation?";
		option1Bank[2] = "The order of amino acids is changed when the protein overheats.";
		option2Bank[2] = "The bonds between amino acids are broken by condensation";
		option3Bank[2] = "Parts of the protein become linked together by hydrolysis";
		option4Bank[2] = "The three-dimensional structure of the protein is altered";
		answerBank[2] = "D";
		
		questionBank1[3] = "This is a sequence of nucleotides from a section of mRNA: AUGAAACGCACGCAG";
		questionBank2[3] = "    From which DNA sequence has it been transcribed?";
		option1Bank[3] = "ATGAAACGCACGCAG";
		option2Bank[3] = "UACUUUGCGUGCGAC";
		option3Bank[3] = "TACUUUGCGTGCGTC";
		option4Bank[3] = "TACTTTGCGTGCGTC";
		answerBank[3] = "D";
		
		questionBank1[4] = "Which gas produces most of the bubbles in bread dough?";
		option1Bank[4] = "Carbon dioxide";
		option2Bank[4] = "Methane";
		option3Bank[4] = "Oxygen";
		option4Bank[4] = "Water vapour";
		answerBank[4] = "A";
		
		questionBank1[5] = "What event occurs only in meiosis?";
		option1Bank[5] = "Fusion of gametes to promote genetic variation";
		option2Bank[5] = "Random separation of chromatids";
		option3Bank[5] = "Random separation of homologous chromosomes";
		option4Bank[5] = "Replication of chromosomes";
		answerBank[5] = "C";
		
		questionBank1[6] = "Which technique separates proteins according to size?";
		option1Bank[6] = "Treatment with restriction endonucleases";
		option2Bank[6] = "Gel electrophoresis";
		option3Bank[6] = "PCR";
		option4Bank[6] = "DNA profiling";
		answerBank[6] = "B";
		
		questionBank1[7] = "What limits the length of food chains in an ecosystem?";
		option1Bank[7] = "The size of individual organisms";
		option2Bank[7] = "Competition between organisms";
		option3Bank[7] = "The loss of energy between trophic levels";
		option4Bank[7] = "Natural selection ";
		answerBank[7] = "C";
		
		questionBank1[8] = "What is a feature of shorter wavelength visible radiation?";
		option1Bank[8] = "It includes violet light.";
		option2Bank[8] = "It has less energy per photon than longer wavelengths.";
		option3Bank[8] = "It is absorbed by greenhouse gases.";
		option4Bank[8] = "It is reflected by chlorophyll.";
		answerBank[8] = "A";
		
		questionBank1[9] = "Which process promotes variation in a population?";
		option1Bank[9] = "Mutation";
		option2Bank[9] = "Mitosis";
		option3Bank[9] = "Ageing in a population";
		option4Bank[9] = "Asexual reproduction";
		answerBank[9] = "A";
		
		questionBank1[10] = "Which discovery was an indication that the heart pumps blood to the body through arteries?";
		option1Bank[10] = "The amount of blood pumped exceeds that of blood produced";
		option2Bank[10] = "Blood could easily be pushed up a limb vein, but not down";
		option3Bank[10] = "The observation that there were pores between the right and left atria";
		option4Bank[10] = "The heart swelled up when the arteries were tied in an animal experiment";
		answerBank[10] = "D";
		// questions selected from: https://freeexampapers.com/exam-papers/IB/Biology/Standard/2016-May/Biology_paper_1__SL.pdf
		// answers from: https://freeexampapers.com/exam-papers/IB/Biology/Standard/2016-May/Biology_paper_1__SL_markscheme.pdf
		
		lblError.setText("");

		if (currentPage == 0|| currentPage == 11 || currentPage == 12) {
			lblAnswer.setText("");
			txtAnswer.setVisible(false);
		}
		else {
			lblAnswer.setText("Answer:");
			txtAnswer.setVisible(true);
		}
		
		if (currentPage > 0 && currentPage <= 10) {
			lblQuestion1.setText(currentPage + ". " + questionBank1[currentPage]);
			lblQuestion2.setText(questionBank2[currentPage]);
			lblAnswer1.setText("A: " + option1Bank[currentPage]);
			lblAnswer2.setText("B: " + option2Bank[currentPage]);
			lblAnswer3.setText("C: " + option3Bank[currentPage]);
			lblAnswer4.setText("D: " + option4Bank[currentPage]);
			txtAnswer.setText(userAnswer[currentPage]);
		}
		else if (currentPage == 0) {
			lblQuestion1.setText(questionBank1[currentPage]);
			lblQuestion2.setText(questionBank2[currentPage]);
			lblAnswer1.setText(option1Bank[currentPage]);
			lblAnswer2.setText(option2Bank[currentPage]);
			lblAnswer3.setText(option3Bank[currentPage]);
			lblAnswer4.setText(option4Bank[currentPage]);
		}
		else if (currentPage == 11) {
			displayAnswerCheck();
			txtAnswer.setText("");
		}
		else if (currentPage == 12) {
			displayStatistics();
			txtAnswer.setText("");
		}
		
		if (currentPage == 0) {
			lblProgress.setText("Instructions");
		}
		else if (currentPage == 11) {
			lblProgress.setText("Check your Answers");
		}
		else if (currentPage == 12) {
			lblProgress.setText("Results");
		}
		else {
			lblProgress.setText("Question " + currentPage + " out of " + NUMBER_OF_QUESTION);
		}
	}
	
	private boolean checkAnswer() {
		String response = txtAnswer.getText();
		boolean valid = false;

		if (response.equals("A") || response.equals("a") || response.equals("A:") || response.equals("a:")) {
			userAnswer[currentPage] = "A";
			valid = true;
		}
		else if (response.equals("B") || response.equals("b") || response.equals("B:") || response.equals("b:")) {
			userAnswer[currentPage] = "B";
			valid = true;
		}
		else if (response.equals("C") || response.equals("c") || response.equals("C:") || response.equals("c:")) {
			userAnswer[currentPage] = "C";
			valid = true;
		}
		else if (response.equals("D") || response.equals("d") || response.equals("D:") || response.equals("d:")) {
			userAnswer[currentPage] = "D";
			valid = true;
		}
		else if (response.equals("")) {
			userAnswer[currentPage] = "";
			valid = true;
		}
		else {
			valid = false;
		}
			
		return valid;
	}
	
	private void displayAnswerCheck() {
		final String SPACE = "                                   ";
		
		lblQuestion1.setText("Please review your answers and click next to submit them or previous to edit your response.");
		lblQuestion2.setText("Once you submit your answers, you will not be able to edit them.");
		
		lblAnswer1.setText("Question 1: " + userAnswer[1] + SPACE + "Question 2: " + userAnswer[2] + SPACE + "Question 3: " + userAnswer[3]);
		lblAnswer2.setText("Question 4: " + userAnswer[4] + SPACE + "Question 5: " + userAnswer[5] + SPACE + "Question 6: " + userAnswer[6]);
		lblAnswer3.setText("Question 7: " + userAnswer[7] + SPACE + "Question 8: " + userAnswer[8] + SPACE + "Question 9: " + userAnswer[9]);
		lblAnswer4.setText("Question 10:" + userAnswer[10]);
	}
	
	private void displayStatistics() {
		DecimalFormat df = new DecimalFormat("###.##");

		int numberCorrect = 0;
		int numberIncorrect = 0;
		int numberBlank = 0;
		
		for (int i = 1; i <= NUMBER_OF_QUESTION; i = i + 1) {
			if (userAnswer[i].equals(answerBank[i])) {
				numberCorrect = numberCorrect + 1;
			}
			else if (userAnswer[i].equals("")) {
				numberBlank = numberBlank + 1;
			}
			else {
				numberIncorrect = numberIncorrect + 1;
			}
		
		lblQuestion1.setText("");
		lblQuestion2.setText("Results:");
		
		lblAnswer1.setText("You answered " + (NUMBER_OF_QUESTION - numberBlank) + " out of " + NUMBER_OF_QUESTION + " questions.");
		lblAnswer2.setText("You got " + numberCorrect + " questions correct and " + numberIncorrect + " questions incorrect.");
		lblAnswer3.setText("Your mark on this quiz is " + df.format(((double)numberCorrect / (double)NUMBER_OF_QUESTION) * 100) + "%.");
		lblAnswer4.setText("Click previous to try again or click next to exit this quiz.");
		}
	}
}
