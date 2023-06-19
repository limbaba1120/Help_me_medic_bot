import i18n from "i18next";
import { initReactI18next } from "react-i18next";

import translationEn from "./translation.en.json";
import translationEs from "./translation.es.json";
import translationFr from "./translation.fr.json";
import translationId from "./translation.id.json";
import translationJp from "./translation.jp.json";
import translationKo from "./translation.ko.json";
import translationPt from "./translation.pt.json";
import translationRu from "./translation.ru.json";
import translationZhCn from "./translation.zh-cn.json";
import translationZhTw from "./translation.zh-tw.json";

const resource = {
  en: {
    translation: translationEn,
  },
  es: {
    translation: translationEs,
  },
  fr: {
    translation: translationFr,
  },
  id: {
    translation: translationId,
  },
  jp: {
    translation: translationJp,
  },
  ko: {
    translation: translationKo,
  },
  pt: {
    translation: translationPt,
  },
  ru: {
    translation: translationRu,
  },
  zhCn: {
    translation: translationZhCn,
  },
  zhTw: {
    translation: translationZhTw,
  },
};

i18n.use(initReactI18next).init({
  resources: resource,
  lng: "en",
  fallbackLng: "en",
  debug: true,
  keySeparator: false,
  interpolation: {
    escapeValue: false,
  },
});

export default i18n;
