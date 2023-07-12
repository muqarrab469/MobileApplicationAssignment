package com.example.mobileapplicationassignment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizFragment extends Fragment {

        private ActivityMainBinding binding;

        TextView message_text,letter_text;


        Button root_btn,grass_btn,sky_btn,play_pause;
        int play_or_pause;
        int cn=0;
        DB dbHelper;

        // RESOURCES.......
        char[] skyLetters = {'b', 'd', 'f', 'h', 'k', 'l', 't'};
        char[] grassLetters = {'g', 'j', 'p', 'q', 'y'};
        char[] rootLetters = {'a', 'c', 'e', 'i', 'm', 'n', 'o', 'r', 's', 'u', 'v', 'w', 'x', 'z'};
        String answerString = "";



        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
      /*  HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
       // homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        */
            return inflater.inflate(R.layout.fragment_quiz,container,false);
        }

        @Override
        public void onViewCreated(@NonNull View view,@NonNull Bundle savedInstance){
            super.onViewCreated(view,savedInstance);

            root_btn=view.findViewById(R.id.root_button);
            play_pause=view.findViewById(R.id.play_pause);
            grass_btn=view.findViewById(R.id.grass_btn);
            sky_btn=view.findViewById(R.id.sky_btn);
            message_text=view.findViewById(R.id.message_text);
            letter_text=view.findViewById(R.id.letter_text);

            dbHelper=new DB(view.getContext());
            cn=0;
            play_pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View insideView) {
                    try {
                        if (play_or_pause == 1) {
                            play_or_pause = 0;
                            message_text.setText("game is finished now !");
                            Helper helper=new Helper();
                            String []ans=helper.converter(historyModels);

                            // now use this db helper to isert data into the database...
                            dbHelper.AddHistory(ans[0],ans[1],cn);
                            play_pause.setText("Play");

                        } else {
                            message_text.setText("Happy Gaming !!");

                            // Wait for 5 seconds and create a new question
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    letter_text.setText(getRandomLetter());
                                    message_text.setText("");
                                    play_or_pause = 1;
                                    play_pause.setText("Finish");
                                }
                            }, 2000); // 5000 milliseconds = 5 seconds


                        }
                    }catch (Exception ex){
                        message_text.setText(ex.getMessage());
                    }
                }
            });
            root_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View inside_view) {
                    if (play_or_pause == 1) {

                        if (answerString == "Root Letter") {
                            message_text.setText("Awesome your answer is right");
                            cn++;
                        } else {
                            message_text.setText("Incorrect! the answer is " + answerString);
                        }

                        historyModels.add(new HistoryModel("Root Letter",answerString));
                        // Wait for 5 seconds and create a new question
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                letter_text.setText(getRandomLetter());
                                message_text.setText("");
                            }
                        }, 2000); // 5000 milliseconds = 5 seconds

                    }else{
                        message_text.setText("First start the game");
                    }
                }
            });

            sky_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (play_or_pause == 1) {

                        if (answerString == "Sky Letter") {
                            message_text.setText("Awesome your answer is right");
                            cn++;
                        } else {
                            message_text.setText("Incorrect! the answer is " + answerString);
                        }

                        historyModels.add(new HistoryModel("Sky Letter",answerString));
                        // Wait for 5 seconds and create a new question
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                letter_text.setText(getRandomLetter());
                                message_text.setText("");
                            }
                        }, 2000); // 5000 milliseconds = 5 seconds

                    }else{
                        message_text.setText("First start the game");
                    }
                }
            });

            grass_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (play_or_pause == 1) {

                        if (answerString == "Grass Letter") {
                            message_text.setText("Awesome your answer is right");
                            cn++;
                        } else {
                            message_text.setText("Incorrect! the answer is " + answerString);
                        }

                        historyModels.add(new HistoryModel("Grass Letter",answerString));
                        // Wait for 5 seconds and create a new question
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                letter_text.setText(getRandomLetter());
                                message_text.setText("");
                            }
                        }, 2000); // 5000 milliseconds = 5 seconds

                    }else{
                        message_text.setText("First start the game");
                    }
                }
            });

        }

        // RANDOM ANSWER STRING GENERATOR.......
        String getRandomLetter() {
            Random random = new Random();
            int category = random.nextInt(3);
            char letter;
            switch (category) {
                case 0:
                    letter = skyLetters[random.nextInt(skyLetters.length)];
                    answerString = "Sky Letter";
                    break;
                case 1:
                    letter = grassLetters[random.nextInt(grassLetters.length)];
                    answerString = "Grass Letter";
                    break;
                default:
                    letter = rootLetters[random.nextInt(rootLetters.length)];
                    answerString = "Root Letter";
                    break;
            }
            return String.valueOf(letter);
        }
        @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }
    }
}