import React from "react";
import axios from "axios";

import profileImage from "../../assets/images/profile.png";

const Comment = ({ postId, commentId, author, content }) => {
  const handleDelete = (event) => {
    event.preventDefault();

    axios
      .delete(
        `https://메디챗.웹.한국:8443/posts/${postId}/comments/${commentId}`
      )
      .then(() => {
        window.location.reload();
      });
  };

  return (
    <div className="flex items-center space-x-3">
      <div className="avatar">
        <div className="w-9 rounded">
          <img src={profileImage} alt="face" />
        </div>
      </div>
      <div className="flex flex-col">
        <div className="flex items-center gap-2">
          <span className="inline-flex text-xs">{author}</span>
          {(author === sessionStorage.getItem("name") ||
            "Admin" === sessionStorage.getItem("name")) && (
            <label className="text-xs" onClick={handleDelete}>
              ✕
            </label>
          )}
        </div>
        <span className="">{content}</span>
      </div>
    </div>
  );
};

export default Comment;
