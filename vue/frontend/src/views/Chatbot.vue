<template>
<html>
  <img :src="require('../assets/TE2.png')" id="background" />
  <img
    :src="require('../assets/chaticon.png')"
    id="display-open-bttn"
    v-show="displayChat"
    v-on:click="openForm()"
  />
  <div id="chat-box">
    <button id="display-close-bttn" v-on:click="closeForm()">Chatbot</button>
    <div class="messages-container" id="messages-container">
      <div class="chat-messages">
        <ul class="messages-list">
          <li
            v-for="message in messageArray"
            v-bind:class="{'bot-message' : message.isBot, 'user-message' : !message.isBot}"
            v-bind:key="message"
          >
            <p>
              {{message.text}}
              <a
                v-if="message.link"
                v-bind:href="message.link"
                target="blank"
              >Click here for more info!</a>
            </p>
          </li>
        </ul>
      </div>
    </div>

    <div class="form">
      <input
        type="text"
        class="message"
        v-model="message"
        v-on:keyup.enter="submitMessage"
        placeholder="Enter your message here"
      />
      <button v-on:click="submitMessage">Send Message</button>
    </div>
  </div>
</html>
</template>

<script>
export default {
  props: {},
  data() {
    return {
      lastMessage: "",
      displayChat: true,
      apiURL:
        "http://localhost:8080/AuthenticationApplication/api/homepage/questions",
      userName: "",
      message: "",
      quoteArr: [],
      isBot: true,
      messageArray: [
        { text: "Hello, my name is Chatbot! What is your name?", isBot: true }
      ],
      helpMessagesArray: [],
      helpRequests: [
        "help",
        "what can i do",
        "assistance",
        "assist",
        "what can i ask",
        "ask",
        "what should i ask",
        "questions"
      ],
      quoteRequests: [
        "motivation",
        "motivational",
        "quote",
        "unmotivated",
        "inspiration"
      ],
      jobSearchRequests: ["job search", "positions", "hiring"],
      emailRequests: ["email", "transcript", "end chat"]
    };
  },
  methods: {
    openForm() {
      document.getElementById("chat-box").style.display = "grid";
      this.displayChat = false;
    },
    submitMessage() {
      if (this.message.length > 0) {
        this.messageArray.push({ text: this.message, link: "", isBot: false });
        if (this.messageArray.length === 2) {
          this.userName = this.message;
          this.messageArray.push({
            text: "Hello there, " + this.userName + "! How can I help?",
            isBot: true
          });
          this.message = "";
        } else {
          this.responseApi();
        }
      } else if (this.lastMessage.length > 0) {
        if (this.messageArray.length === 2) {
          this.userName = this.lastMessage;
          this.messageArray.push({
            text: "Hello there, " + this.userName + "! How can I help?",
            isBot: true
          });
        } else {
          this.responseApi();
        }
      }

      setTimeout(this.gotoBottom, 500);
      // this.textApi(this.messageArray);
    },
    displayOther() {
      if (
        new RegExp(this.helpRequests.join("|")).test(this.message.toLowerCase())
      ) {
        let options = "";
        let i = 1;
        this.helpMessagesArray.forEach(element => {
          if (i === this.helpMessagesArray.length) {
            options += " or " + element + ".";
          } else {
            options += element + ", ";
          }
          i++;
        });
        this.messageArray.push({
          text: "Try asking about: " + options,
          isBot: true
        });
      } else if (
        new RegExp(this.quoteRequests.join("|")).test(
          this.message.toLowerCase()
        )
      ) {
        this.quoteApi(this.messageArray);
      } else if (
        new RegExp(this.jobSearchRequests.join("|")).test(
          this.message.toLowerCase()
        )
      ) {
        this.jobApi(this.messageArray);
      } else if (
        new RegExp(this.emailRequests.join("|")).test(
          this.message.toLowerCase()
        )
      ) {
        this.sendJSON();
      } else if (
        new RegExp(this.helpRequests.join("|")).test(this.lastMessage.toLowerCase())
      ) {
        let options = "";
        let i = 1;
        this.helpMessagesArray.forEach(element => {
          if (i === this.helpMessagesArray.length) {
            options += " or " + element + ".";
          } else {
            options += element + ", ";
          }
          i++;
        });
        this.messageArray.push({
          text: "Try asking about: " + options,
          isBot: true
        });
      } else if (
        new RegExp(this.quoteRequests.join("|")).test(
          this.lastMessage.toLowerCase()
        )
      ) {
        this.quoteApi(this.messageArray);
      } else if (
        new RegExp(this.jobSearchRequests.join("|")).test(
          this.lastMessage.toLowerCase()
        )
      ) {
        this.jobApi(this.messageArray);
      } else if (
        new RegExp(this.emailRequests.join("|")).test(
          this.lastMessage.toLowerCase()
        )
      ) {
        this.sendJSON();
      } else {
        this.messageArray.push({
          text: "I don't understand. Could you rephrase that for me?",
          isBot: true
        });
      }
      this.message = "";
    },
    closeForm() {
      document.getElementById("chat-box").style.display = "none";
      this.displayChat = true;
    },
    gotoBottom() {
      var element = document.getElementById("messages-container");
      element.scrollTop = element.scrollHeight - element.clientHeight;
    },
    responseApi() {
      let param = this.messageArray[this.messageArray.length - 1].text
        .split(" ")
        .join("-");
      fetch(
        "http://localhost:8080/AuthenticationApplication/api/homepage/answers?question=" +
          param
      )
        .then(response => {
          return response.json();
        })
        .then(item => {
          if (item.message === "Not Found") {
            console.log(item.message);
            this.displayOther();
          } else {
            this.messageArray.push({
              text: item.message,
              link: item.link,
              isBot: true
            });
          }
          this.message = "";
          setTimeout(this.gotoBottom, 500);
        })
        .catch(err => console.log(err));
    },
    quoteApi(messageArray) {
      fetch("https://type.fit/api/quotes")
        .then(function(response) {
          return response.json();
        })
        .then(function(data) {
          let arrayLength = data.length;
          let randomQuote =
            data[Math.floor(Math.random() * (arrayLength - 0) + 0)];
          messageArray.push({
            text: "Need motivation? How about this quote I found inspiring?",
            isBot: true
          });
          messageArray.push({
            text: '"' + randomQuote.text + '"' + " - " + randomQuote.author,
            isBot: true
          });
        });
    },
    jobApi(messageArray) {
      fetch(
        "http://localhost:8080/AuthenticationApplication/api/homepage/jobsearch"
      )
        .then(response => {
          return response.json();
        })
        .then(response => {
          messageArray.push({
            text: "Looking for a job? Here's some open positions I found!",
            isBot: true
          });
          response.forEach(x => {
            messageArray.push({
              text: x.company + " - " + x.title,
              link: x.url,
              isBot: true
            });
          });
        })
        .catch(err => console.log(err));
    },
    textApi(messageArray) {
      fetch("https://textbelt.com/text", {
        method: "post",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          phone: "4153856918",
          message: messageArray[this.messageArray.length - 1].text,
          key:
            "530c7f34bf9050c85190794dcc85f1397a3758d1i90imlddr0ahCdgrzzeZlPmZC",
          replyWebhookUrl: "http://maxkohl.maxkohl.ultrahook.com"
        })
      })
        .then(response => {
          return response.json();
        })
        .then(data => {
          console.log(data);
        });
    },
    getTextApi() {
      fetch(
        "http://localhost:8080/AuthenticationApplication/api/homepage/getText"
      )
        .then(response => {
          return response.json();
        })
        .then(response => {
          let currentMessage = response[response.length - 1];
          if (
            this.lastMessage !== currentMessage &&
            currentMessage !== undefined
          ) {
            this.lastMessage = currentMessage;
            this.messageArray.push({
              text: currentMessage,
              isBot: false
            });
            this.submitMessage();
          }
        });
      // .catch(err => console.log(err));
    },
    sendJSON() {
      let xhr = new XMLHttpRequest();
      let url =
        "http://localhost:8080/AuthenticationApplication/api/homepage/sendEmail";
      xhr.open("POST", url, true);
      xhr.setRequestHeader("Content-Type", "application/json");

      var dataJSON = JSON.stringify(this.messageArray);

      xhr.send(dataJSON);
      
      this.messageArray = [{ text: "Hello, my name is Chatbot! What is your name?", isBot: true }];
    }
  },
  computed: {},
  created() {
    setInterval(this.getTextApi, 1000);
    fetch(this.apiURL)
      .then(response => {
        return response.json();
      })
      .then(items => {
        this.helpMessagesArray = items;
      })
      .catch(err => console.log(err));
  }
};
</script>


<style>
#background {
  background-image: url("../assets/TE2.png");
  background-repeat: no-repeat;
  background-attachment: inherit;
}
#chat-box {
  background-color: rgb(243, 243, 243);
  border: 1px solid black;
  width: 400px;
  height: 400px;
  border-radius: 5px;
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: auto 3fr auto;
  grid-template-areas:
    "Header"
    "messages"
    "userInput";
  position: absolute;
  bottom: 0;
  right: 0;
}

.messages-container {
  grid-area: messages;
  overflow-y: auto;
  overflow-x: hidden;
}

.messages-list {
  padding: 0px;
  margin: 10px;
}

ul {
  list-style: none;
}

.form {
  margin-top: 5px;
  width: 100%;
  grid-area: userInput;
  text-align: right;
}

input {
  color: black;
}

li {
  display: block;
  margin: 2px 0px 2px 0px;
  -webkit-border-radius: 4px;
  border-radius: 4px;
  line-height: 1.3;
  position: relative;
}

.message {
  background: #f4f7f9;
  width: 60%;
  position: relative;
  height: 20px;
  padding: 10px 50px 10px 15px;
  border: none;
  resize: none;
  outline: none;
  border: 1px solid #ccc;
  border-top: none;
  border-bottom-right-radius: 5px;
  border-bottom-left-radius: 5px;
  overflow: hidden;
}

.bot-message {
  text-align: left;
  padding-right: 5px;
}

.user-message {
  text-align: right;
  padding-left: 5px;
}

div > ul > li.user-message > p {
  display: inline-block;
  border-radius: 5px;
  border: thin 1px black;
  padding: 3px 3px 3px 5px;
  margin: 5px;
  background-color: #44b1f0;
}

div > ul > li.bot-message > p {
  display: inline-block;
  border-radius: 5px;
  border: thin 1px black;
  padding: 3px 5px 3px 3px;
  margin: 5px;
  background-color: #e5e5ea;
}

li.user-message > p::after {
  border-left: 10px solid transparent;
  border-top: 10px solid #44b1f0;
  bottom: -3px;
  content: "";
  position: absolute;
  right: 10px;
}

li.bot-message > p::after {
  border-right: 10px solid transparent;
  border-top: 10px solid #e5e5ea;
  bottom: -3px;
  content: "";
  position: absolute;
  left: 10px;
}

button {
  grid-area: Header;
  height: 40px;
}

#display-close-bttn {
  background: #44b1f0;
  height: 40px;
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
  color: white;
  text-align: center;
  font-size: 20px;
  padding-top: 4px;
  margin: 0;
}

#display-open-bttn {
  position: absolute;
  bottom: 0;
  right: 0;
  background: #44b1f0;
  height: 40px;
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
  color: white;
  text-align: center;
  font-size: 20px;
  padding-top: 4px;
  margin: 0;
}

a:-webkit-any-link {
  overflow-anchor: initial;
}
</style>