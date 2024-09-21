import { PointInterest } from "./PointInterest";
import { Position } from "./Position";

export interface CarStayTime{
  plate: string,
  timeStay: string,
  pointInterest:PointInterest,
  lastPosition:Position;
}
