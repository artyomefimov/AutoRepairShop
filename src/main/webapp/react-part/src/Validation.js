import * as Yup from "yup";

export const workshopSchema = Yup.object().shape({
  inn: Yup.number()
    .required("Введите ИНН мастерской!")
    .integer("ИНН должен быть целым числом!")
    .max(999999, "ИНН должен иметь до 6 цифр!")
    .positive("ИНН должен быть положительным числом!"),
  name: Yup.string()
    .trim()
    .min(3, "Название мастерской должно иметь минимум 3 символа!")
    .max(30, "Название мастерской должно иметь максимум 60 символов!")
    .required("Введите название мастерской!"),
  address: Yup.string()
    .trim()
    .min(6, "Адрес мастерской должен иметь минимум 6 символов!")
    .max(60, "Адрес мастерской должен иметь максимум 60 символов!")
    .required("Введите адрес мастерской!"),
  openHours: Yup.string().required("Введите время открытия!"),
  closeHours: Yup.string().required("Введите время закрытия!"),
  ownerName: Yup.string()
    .trim()
    .max(60, "Имя владельца мастерской должно иметь максимум 60 символов!")
  // .test(
  //   "test closeHours",
  //   "Время закрытия должно быть позднее времени",
  //   value => {
  //     let openHoursValue = Yup.ref("openHours");
  //     let { openHH, openMM } = openHoursValue.split(":");
  //     let { closeHH, closeMM } = value.split(":");
  //     return openHH <= closeHH && openMM < closeMM;
  //   }
  // )
});

export const levelSchema = Yup.object().shape({
  name: Yup.string()
    .trim()
    .min(3, "Название квалификации должно иметь минимум 3 символа!")
    .max(60, "Название квалификации должно иметь максимум 60 символов!")
    .required("Введите название квалификации!")
});

export const customerSchema = Yup.object().shape({
  series: Yup.number()
    .required("Введите серию паспорта клиента!")
    .min(1000, "Серия паспорта начинается с 1000!")
    .max(9999, "Максимальная серия паспорта - 9999!"),
  num: Yup.number()
    .required("Введите номер паспорта клиента!")
    .min(100000, "Серия паспорта начинается с 100000!")
    .max(999999, "Максимальная серия паспорта - 999999!"),
  name: Yup.string()
    .trim()
    .min(3, "Имя клиента должно иметь минимум 3 символа!")
    .max(60, "Имя клиента должно иметь максимум 60 символов!")
    .required("Введите имя клиента!"),
  phone: Yup.number()
    .required("Введите номер телефона клиента!")
    .min(1000000000, "Номера телефонов начинаются с 1000000000!")
    .max(9999999999, "Максимально возможный номер телефона - 9999999999!"),
  address: Yup.string()
    .trim()
    .max(60, "Адрес клиента должен иметь максимум 60 символов!")
});
