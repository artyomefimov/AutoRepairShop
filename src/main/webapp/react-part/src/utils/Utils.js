export function splitPassportNumOnSeriesAndNum(passportNum) {
  return {
    series: passportNum.toString().substring(0, 4),
    num: passportNum.toString().substring(4, passportNum.length)
  };
}

export function getDayMonthAndYearFromDateAsLong(dateLong) {
  var date = new Date(dateLong);
  return (
    date.getDate() +
    " " +
    getStringMonthName(date.getUTCMonth() + 1) +
    " " +
    date.getUTCFullYear()
  );
}

function getStringMonthName(monthNumber) {
  switch (monthNumber) {
    case 1:
      return "Января";
    case 2:
      return "Февраля";
    case 3:
      return "Марта";
    case 4:
      return "Апреля";
    case 5:
      return "Мая";
    case 6:
      return "Июня";
    case 7:
      return "Июля";
    case 8:
      return "Августа";
    case 9:
      return "Сентября";
    case 10:
      return "Октября";
    case 11:
      return "Ноября";
    case 12:
      return "Декабря";
    default:
      return "";
  }
}

export function getDayMonthAndYearFromDate(date) {
  var day = date.getDate();
  if (day < 10) day = "0" + day;
  var month = date.getUTCMonth() + 1;
  if (month < 10) month = "0" + month;
  return date.getUTCFullYear() + "-" + month + "-" + day;
}

export function resolveParentObjectName(parentObjectName) {
  return parentObjectName.substring(parentObjectName.indexOf('='), parentObjectName.length)
}