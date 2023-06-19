import React, { useState, useEffect } from "react";
import axios from "axios";

import Post from "./Post";

const PostList = () => {
  let [postList, setPostList] = useState([]);

  useEffect(() => {
    axios.get("https://메디챗.웹.한국:8443/posts").then((res) => {
      setPostList(res.data.postList);
    });
  }, []);

  return (
    <>
      {postList.map((post) => (
        <Post
          key={post.id}
          id={post.id}
          author={post.author}
          content={post.content}
        />
      ))}
    </>
  );
};

export default PostList;
