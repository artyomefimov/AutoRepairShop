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
      .min(6, "Адрес мастерской должно иметь минимум 6 символов!")
      .max(60, "Адрес мастерской должно иметь максимум 60 символов!")
      .required("Введите адрес мастерской!"),
    openHours: Yup.string().required("Введите время открытия!"),
    closeHours: Yup.string().required("Введите время закрытия!"),
    ownerName: Yup.string()
      .trim()
      .max(
        60,
        "Имя владельца мастерской должно иметь максимум 60 символов!"
      )
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
      .max(
        60,
        "Название квалификации должно иметь максимум 60 символов!"
      )
      .required("Введите название квалификации!")
  });