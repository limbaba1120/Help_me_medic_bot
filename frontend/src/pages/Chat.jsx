import React, { useState } from "react";

import ChatLog from "../components/chat/ChatLog";
import ChatForm from "../components/chat/ChatForm";

const Chat = () => {
  const [chatLog, setChatLog] = useState([]);

  const handleUpdate = (type, message) => {
    setChatLog((prevChatLog) => [
      ...prevChatLog,
      { id: type + Math.random(), type: type, value: message },
    ]);
  };

  return (
    <section className="msger w-full max-w-3xl mx-auto flex flex-col gap-8">
      <ChatLog log={chatLog} />
      <ChatForm onUpdate={handleUpdate} />
    </section>
  );
};

export default Chat;
