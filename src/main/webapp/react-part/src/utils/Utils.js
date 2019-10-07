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
  return "\"" + decodeURI(parentObjectName.substring(parentObjectName.indexOf('=') + 1, parentObjectName.length)) + "\"";
}

export function resolveParentObjectTypeAndName(parentObjectName, url) {
  var parentType = resolveParentObjectType(url)
  var type = ""
  if (parentType === "workshop") {
    type = " мастерской "
  } else if (parentType === "level") {
    type = " квалификации "
  }
  else if (parentType === "customer") {
    type = " клиента "
  } else if (parentType === "master") {
    type = " мастера "
  }
  return ` ${type}"${decodeURI(parentObjectName.substring(parentObjectName.indexOf('=') + 1, parentObjectName.length))}"`;
}

export function resolveParentObjectType(url) {
  var parts = url.split('/');
  return parts[1];
}

export function resolvePreviousLocation(locationUrl, url) {
  var type = locationUrl.substring(locationUrl.indexOf('?') + 1, locationUrl.indexOf('='));
  var id = locationUrl.substring(locationUrl.indexOf('=') + 1, locationUrl.length);
  var parentType = resolveParentObjectType(url) + "s";
  return `${type}/${id}/${parentType}`;
}