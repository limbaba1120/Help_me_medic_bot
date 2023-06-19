import React from "react";

import PostForm from "../components/forum/PostForm";
import PostList from "../components/forum/PostList";

const Forum = () => {
  return (
    <div className="flex flex-col gap-4 pb-16">
      <PostForm />
      <PostList />
    </div>
  );
};

export default Forum;
