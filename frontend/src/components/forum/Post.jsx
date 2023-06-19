import React from "react";
import axios from "axios";

import profileImage from "../../assets/images/profile.png";
import CommentForm from "./CommentForm";
import CommentList from "./CommentList";

const Post = ({ id, author, content }) => {
  const handleDelete = (event) => {
    event.preventDefault();

    axios.delete(`https://메디챗.웹.한국:8443/posts/${id}`).then(() => {
      window.location.reload();
    });
  };

  return (
    <div className="card w-full bg-base-100 shadow-xl">
      <div className="card-body">
        <div className="flex items-center space-x-3">
          <div className="avatar">
            <div className="w-9 rounded">
              <img src={profileImage} alt="" />
            </div>
          </div>
          <p>{author}</p>
          {(author === sessionStorage.getItem("name") ||
            "Admin" === sessionStorage.getItem("name")) && (
            <label
              className="btn btn-outline btn-xs m-1"
              onClick={handleDelete}
            >
              Delete
            </label>
          )}
        </div>
        <p className="my-2 text-lg">{content}</p>
        <CommentForm id={id} />
        <div className="flex flex-col mt-2 gap-4">
          <CommentList id={id} />
        </div>
      </div>
    </div>
  );
};

export default Post;
