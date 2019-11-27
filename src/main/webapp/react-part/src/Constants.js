//spring url
export const AUTOREPAIR_API_URL = 'http://localhost:8080'

export const UNAUTHORIZED = 401;

// page names
export const workshopListPageName = "Мастерские";
export const workshopDetailPageName = "Редактировать мастерскую";
export const levelListPageName = "Квалификации";
export const levelDetailPageName = "Редактировать квалификацию";
export const customerListPageName = "Клиенты мастерской ";
export const customerDetailsPageName = "Редактировать клиента";
export const masterListPageName = "Мастера";
export const masterDetailsPageName = "Редактировать мастера";
export const carListPageName = "Автомобили";
export const carDetailsPageName = "Редактировать автомобиль"

// table headers
export const workshopListTableHeaders = [
  "ИНН",
  "Название",
  "Адрес",
  "Открывается в",
  "Закрывается в",
  "Имя владельца",
  "",
  "",
  "",
  ""
];
export const levelListTableHeaders = ["Название", "", "", ""];
export const customerListTableHeaders = [
  "Номер паспорта",
  "Имя",
  "Номер телефона",
  "Адрес",
  "Дата рождения",
  "",
  "",
  ""
];
export const masterListTableHeaders = [
  "Номер паспорта",
  "Имя",
  "Номер телефона",
  "",
  "",
  ""
];
export const carListTableHeaders = [
  "Номер автомобиля",
  "Марка",
  "Модель",
  "Тип поломки",
  "Пробег",
  "",
  ""
];
