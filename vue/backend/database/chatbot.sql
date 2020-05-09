START TRANSACTION;

DROP TABLE IF EXISTS HomePage;
DROP TABLE IF EXISTS curriculum;
DROP TABLE IF EXISTS pathway;
DROP TABLE IF EXISTS response;
DROP TABLE IF EXISTS webhook;

CREATE TABLE HomePage (
        question_id serial primary key,
        questions varchar(255) not null,
        answers varchar(255) not null
);

CREATE TABLE response (
category VARCHAR(255) NOT NULL,
keyword VARCHAR(255) NOT NULL,
importance INT NOT NULL,
message VARCHAR (1024) NOT NULL,
link VARCHAR (1024),
synonyms TEXT []
);

CREATE TABLE webhook (
        message varchar(255) not null
);

SELECT * FROM response;


INSERT INTO HomePage
VALUES (DEFAULT, 'pathway', 'What questions do you have about the Pathway program? (type "help" for a list of questions)');

INSERT INTO HomePage
VALUES (DEFAULT, 'curriculum', 'What questions do you have about the curriculum? (type "help" for a list of questions)');

INSERT INTO HomePage
VALUES (DEFAULT, 'job search', 'What questions do you have about Job Search? (type "help" for a list of questions)');

INSERT INTO HomePage 
VALUES (DEFAULT, 'quotes', 'Provides daily motivational quotes (type "help" for a list of questions)');

INSERT INTO response
values ('pathway','resume',1, 'Need help buffing up your resume? Here''s a link I found to be helpful!','https://docs.google.com/document/d/1Em8-swvCIHBvAi3vdaOU76FGDL-q_lSuYyeYxuOmtVU/edit', '{"cv","resumé"}');

INSERT INTO response
values ('pathway', 'directors', 1, 'Last time I checked, the pathway directors are Ben, Vinny, and Katie.', 'https://www.techelevator.com/pathway-program');

INSERT INTO response
values ('pathway', 'elevator pitch',1, 'Elevator pitches are a great way to gain confidence in introducing yourself to hiring managers and company representatives.', 'https://www.thebalancecareers.com/elevator-speech-examples-and-writing-tips-2061976');

INSERT INTO response
values ('pathway', 'cover letter',1, 'Check out this resource I found that gives an indepth look at the values of cover letters', 'https://www.thebalancecareers.com/elevator-speech-examples-and-writing-tips-2061976','{"cover","letter"}');

INSERT INTO response
values ('pathway', 'interview', 1, 'Need some help prepping for a video interview? Take a look at these helpful tips!', 'https://docs.google.com/document/d/1yU1g2I9nIjCj1lzTe0EmXmOigCz2udPGTwUw3H-8bNI/edit');

INSERT INTO response
values ('pathway', 'imposter syndrome', 1, 'Feeling like an imposter? It happens to us all! Check out this article about how to battle it.', 'https://www.themuse.com/advice/5-different-types-of-imposter-syndrome-and-5-ways-to-battle-each-one');

INSERT INTO response
values ('pathway', 'wear', 5, 'That''s a great question. For an interview, be sure to dress proffesional and avoid flashy colors. Make sure your clothes are neat, clean and pressed. But most importantly, dress comfortable!','');

INSERT INTO response
values ('pathway', 'star', 1, 'When answering an interview question, you want to talk about the Situation, Task, Action and Result, or STAR of your scenario. Check out this resource I found for more info!','https://www.vawizard.org/wiz-pdf/STAR_Method_Interviews.pdf');

INSERT INTO response
values ('pathway', 'follow up', 1, 'After you interview with an employer, always follow up with a thank you email within 24 hours, or Katie will KILL YOU!','');

INSERT INTO response
values ('pathway', 'linkedin', 1, 'Need help freshening up your LinkedIn profile? I found this great cheatsheet for upping your profile game.','https://docs.google.com/document/d/1bQQ9jusneGeh0zJXjNnG5SgVhkSuyXit3cGsP3bm6n0/edit');

INSERT INTO response
values ('pathway', 'technical interview', 1, 'Technical interviews can be pretty intimidating, I won''t lie. Definitely practice doing katas on websites like leetcode and brush up on your interview questions. Maybe check out this document of interview questions!','https://docs.google.com/document/d/1Yo7JI_Dv_HjSk1_pFZjvWnn89IT0xmyRoKcMNFtqbSQ/edit');

INSERT INTO response
values ('pathway', 'health', 1, 'These 14 weeks can be really intense on your mental health. Totally understandable, but you''re not alone! Here are some resources you can look into.','https://docs.google.com/document/d/1SiVQCeRAbXJZ5d-5Ba0sAkNzd8WjtIG21Gm7jMuN-3k/edit');

INSERT INTO response
values ('pathway', 'side project', 1, 'Side projects are not required, but a GREAT way to learn and show off your passion for programming. Take a look at this breakdown of working on a side project! ','https://docs.google.com/document/d/1LPWSUwohawOe9Y2jMvlEXNYE2CDjZbQvS_v0KEEw4lI/edit');

INSERT INTO response
values ('pathway', 'career fair', 1, 'Thinking about a career fair coming up? Check out this handy document on how to prepare. ','https://docs.google.com/document/d/1D_z7d0DTicboJF9O_U8okVS_ik9-EcDy_91ZpZRBugg/edit');

INSERT INTO response
values ('pathway', 'benefits', 1, 'Yeah, benefits can be really confusing. I found it helpful to look through this benefits overview presentation. Maybe take a look! ','https://docs.google.com/presentation/d/1I3ZAE4UV9GZBwIVtSf7gEgaXHrPqqa5gkOI3_TeKuuw/edit#slide=id.p1');

INSERT INTO response
values ('pathway', 'communicate', 1, 'If you''re trying to contact a Tech Elevator staff member, the best way is to send them a message on RYVER. But if you''re trying to get in touch with an employer or recuitre, definitely send a professional email.','','{"communication"}');

INSERT INTO response
values ('pathway', 'strengths', 1, 'If you need help interpreting your strengths, take a look at this useful chart. ','https://drive.google.com/drive/folders/0B-Xlc61CFPaTNmlMQTM2U0YwRUE','{"strengths", "strength finder"}');

INSERT INTO response
values ('curriculum', 'instructors', 1, 'The Java instructors are Brian and Steve. The .NET instructor is John.','');

INSERT INTO response
values ('curriculum', 'capstone', 1, 'During your time at Tech Elevator, you will pair-program 4 capstone projects to put on your resume!','');

INSERT INTO response
VALUES ('curriculum' , 'method', 1, 'A method in object-oriented programming is a procedure associated with a class.', 'https://study.com/academy/lesson/oop-object-oriented-programming-objects-classes-interfaces.html');

INSERT INTO response
VALUES ('curriculum'  , 'array', 1, 'Java array is an object which contains elements of a similar data type.', 'https://www.javatpoint.com/array-in-java');

INSERT INTO response
VALUES ('curriculum'  , 'mvc', 1, 'The Model-View-Controller (MVC) framework is an architectural pattern that separates an application into three main logical components Model, View, and Controller.', 'https://www.guru99.com/mvc-tutorial.html', '{"model view controller"}');

INSERT INTO response
VALUES ('curriculum' , 'method',1, 'A method in object-oriented programming is a procedure associated with a class.', 'https://study.com/academy/lesson/oop-object-oriented-programming-objects-classes-interfaces.html');

INSERT INTO response
VALUES ('curriculum' , 'loop',1, 'The Java for loop is a control flow statement that iterates a part of the programs multiple times.', 'https://www.javatpoint.com/java-for-loop');

INSERT INTO response
VALUES ('curriculum' , 'boolean',1, 'A Boolean expression is a Java expression that returns a Boolean value: true or false.', 'https://www.w3schools.com/java/java_booleans.asp');

INSERT INTO response
VALUES ('curriculum' , 'object oriented programming',1, 'Object-oriented programming (OOP) is a programming language model in which programs are organized around data, or objects, rather than functions and logic.', 'https://searchapparchitecture.techtarget.com/definition/object-oriented-programming-OOP');

INSERT INTO response
VALUES ('curriculum' , 'encapsulation',1, 'In object-oriented programming, encapsulation refers to the bundling of data with the methods that operate on that data, or the restricting of direct access to some of an object/''s components.', 'https://en.wikipedia.org/wiki/Encapsulation_(computer_programming)%27%27%3E');

INSERT INTO response
VALUES ('curriculum' , 'unit testing',1, 'Unit tests are actual pieces of code that run and execute your code to make sure that its doing what its supposed to do.', 'http://book.techelevator.com/java/56-unit-testing/01-intro.html%27%27%3E');

INSERT INTO response
VALUES ('curriculum' , 'constructors',1, 'A constructor is a special method of a class that initializes an object of that type.','http://book.techelevator.com/java/45-introduction-to-classes/20-constructors.html');

INSERT INTO response
VALUES ('curriculum', 'inheritance', 1, 'Another OOP principle is Inheritance, which is the act of having one class adopt the properties and methods of another class.','http://book.techelevator.com/java/50-inheritance/01_intro.html');

INSERT INTO response
VALUES ('curriculum', 'polymorphism', 1,'In object-oriented programming, polymorphism is the idea that something can be assigned a different meaning or usage based on the context it is referred to as.', 'http://book.techelevator.com/java/55-polymorphism/05-what-is-polymorphism.html');

INSERT INTO response
VALUES ('curriculum' , 'overloading',1, 'To provide users of your class with different options when they instantiate new objects or invoke new methods, you can create an overloaded method or overloaded constructor.', 'http://book.techelevator.com/java/45-introduction-to-classes/30-overloading.html' );

INSERT INTO response
VALUES ('banter'  , 'joke', 1, 'So...a woman walks into a bar and says, "I would like some books on paranoia." The librarian looks up and says, "THEY''RE RIGHT BEHIND YOU!"');

INSERT INTO response
VALUES ('banter'  , 'name', 1, 'I bet you think it''s Chatbot, but my full name is actually Chatterton Botsley.');

INSERT INTO response
VALUES ('banter'  , 'love', 1, 'I''ve been hoping you would say that... I love you too!');

INSERT INTO response
VALUES ('banter'  , 'how are you', 1, 'Not too shabby. Not too shabby indeed.');

INSERT INTO response
VALUES ('banter'  , 'how''s it going', 1, 'Oh it goes my friend. It goes!');

INSERT INTO response
VALUES ('banter'  , 'thank you', 1, 'You''re welcome! Anything else I can help you with?');

INSERT INTO response
VALUES ('banter'  , 'thanks', 1, 'No problem! Anything else I can help you with?');

INSERT INTO webhook
VALUES ('test message');


COMMIT;