import React, { useRef, useEffect } from "react";

import ReqChatBubble from "./ReqChatBubble";
import ResChatBubble from "./ResChatBubble";
import "../../styles/ChatLog.css";

const ChatLog = ({ log }) => {
  const messageEndRef = useRef(null);

  useEffect(() => {
    messageEndRef.current.scrollIntoView({ behavior: "smooth" });
  }, [log]);

  return (
    <main className="msger-chat">
      {log.map((chat) => {
        if (chat.type === "req") {
          return <ReqChatBubble key={chat.id} message={chat.value} />;
        } else {
          return <ResChatBubble key={chat.id} message={chat.value} />;
        }
      })}
      <div ref={messageEndRef}></div>
    </main>
  );
};
export default ChatLog;
