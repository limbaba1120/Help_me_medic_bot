import React, { useCallback, useEffect, useMemo, useState } from "react";
import { useMediaQuery } from "react-responsive";
import { themeChange } from "theme-change";

import { FaSun, FaMoon } from "react-icons/fa";

const ModeToggleButton = () => {
  const THEME_LIGHT = useMemo(() => "lofi", []);
  const THEME_DARK = useMemo(() => "business", []);
  const [isDarkMode, setIsDarkMode] = useState(false);
  const systemPreference = useMediaQuery({
    query: "(prefers-color-scheme: dark)",
  });

  useEffect(() => {
    themeChange(false);
  }, []);

  useEffect(() => {
    const osTheme = systemPreference ? THEME_DARK : THEME_LIGHT;
    const userTheme = localStorage.getItem("theme");
    const theme = userTheme || osTheme;
    setIsDarkMode(() => theme === THEME_DARK);
  }, [systemPreference, THEME_LIGHT, THEME_DARK]);

  const handleTheme = useCallback(() => {
    setIsDarkMode((prev) => {
      localStorage.setItem("theme", prev ? THEME_LIGHT : THEME_DARK);
      return !prev;
    });
  }, [THEME_LIGHT, THEME_DARK]);

  return (
    <div
      className="btn btn-square btn-ghost w-12 h-10 rounded-lg"
      role="presentation"
      data-set-theme={isDarkMode ? THEME_LIGHT : THEME_DARK}
      data-act-class="ACTIVECLASS"
      onClick={() => handleTheme()}
    >
      {isDarkMode ? <FaSun size="20px" /> : <FaMoon size="20px" />}
    </div>
  );
};

export default ModeToggleButton;
