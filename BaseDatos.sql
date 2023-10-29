-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-10-2023 a las 03:23:16
-- Versión del servidor: 8.0.30
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `nutricionista`
--
CREATE DATABASE IF NOT EXISTS `nutrigrupo43` DEFAULT CHARACTER SET utf8mb4 ;
USE `nutrigrupo43`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pacientes`
--

DROP TABLE IF EXISTS `pacientes`;
CREATE TABLE `pacientes` (
  `idpaciente` int NOT NULL,
  `nombre` varchar(60) DEFAULT NULL,
  `dni` int NOT NULL,
  `domicilio` varchar(60) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `estado` tinyint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;



-- -----------------------------------------------------------

--
-- Estructura de tabla para la tabla `dietas`
--

DROP TABLE IF EXISTS `dietas`;
CREATE TABLE `dietas` (
  `iddieta` int NOT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `idpaciente` int DEFAULT NULL,
  `fecinicio` date DEFAULT NULL,
  `fecfinal` date DEFAULT NULL,
  `pesoinicial` double DEFAULT NULL,
  `pesofinal` double DEFAULT NULL,
  `estado` tinyint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;




-- ---------------------------------------------------------



--
-- Estructura de tabla para la tabla `comidas`
--

DROP TABLE IF EXISTS `comidas`;
CREATE TABLE `comidas` (
  `idcomida` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `receta` varchar(120) DEFAULT NULL,
  `calorias` int DEFAULT NULL,
  `estado` tinyint DEFAULT NULL,
  `peso` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

--
-- Volcado de datos para la tabla `comidas`
--

INSERT INTO `comidas` (`idcomida`, `nombre`, `receta`, `calorias`, `estado`, `peso`) VALUES
(1, 'Ensalada de Garbanzos y Aguacate', 'Garbanzos cocidos, aguacate, tomate, cilantro, limón', 340, 1, 280),
(2, 'Pescado a la Parrilla con Espárragos', 'Filete de pescado, espárragos, quinua cocida, limón', 410, 1, 380),
(3, 'Ensalada de Espinacas con Fresas', 'Espinacas frescas, fresas, almendras tostadas', 350, 1, 380),
(4, 'Pavo al Horno con Batatas', 'Pechuga de pavo, batatas asadas, romero, aceite de oliva', 420, 1, 350),
(5, 'Ensalada de Col Rizada y Aguacate', 'Col rizada, aguacate, nueces, vinagreta de limón', 380, 1, 280),
(6, 'Tofu Salteado con Verduras', 'Tofu, brócoli, zanahorias, pimientos, salsa de soja baja en sodio', 290, 1, 320),
(7, 'Ensalada de Atún y Garbanzos', 'Atún enlatado, garbanzos, tomate, cebolla roja, aceite de oliva', 320, 1, 280),
(8, 'Pollo al Curry con Brócoli', 'Pechuga de pollo, brócoli, cebolla, curry en polvo, leche de coco', 520, 1, 400),
(9, 'Quinua con Verduras Asadas', 'Quinua cocida, zanahorias, calabacines, pimientos, aceite de oliva', 380, 1, 3000),
(10, 'Salmón a la Parrilla con Espárragos', 'Filete de salmón, espárragos, aceite de oliva, limón', 4500, 1, 350),
(11, 'Ensalada de Espinacas con Fresas', 'aEspinacas frescas, fresas, almendras tostadas', 350, 1, 250),
(25, 'Pechuga de Pollo a la Plancha', 'Pechuga de pollo asada a la parrilla con especias.', 300, 1, 180),
(26, 'Tofu Salteado con Vegetales', 'Tofu salteado con brócoli, pimientos y salsa de soja baja en sodio.', 220, 1, 160),
(27, 'Hummus con Zanahorias', 'Hummus casero con zanahorias frescas en rodajas.', 180, 1, 220),
(28, 'Sopa de Lentejas', 'Sopa nutritiva de lentejas con verduras y especias.', 280, 1, 240),
(29, 'Filete de Salmón al Horno', 'Filete de salmón horneado con limón y eneldo.', 320, 1, 170),
(30, 'Pavo Asado con Batata', 'Pavo asado con batatas y hierbas aromáticas.', 280, 1, 210),
(31, 'Ensalada de Frutas Frescas', 'Mezcla de frutas frescas como piña, fresas, kiwi y uvas.', 120, 1, 180),
(32, 'Salmón a la Parrilla', 'Filete de salmón a la parrilla con limón y especias.', 350, 1, 200),
(33, 'Yogur Griego con Frutas', 'Yogur griego bajo en grasa con fresas y arándanos.', 150, 1, 200),
(34, 'Brócoli al Vapor', 'Brócoli fresco cocido al vapor con un toque de aceite de oliva.', 40, 0, 150);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `controles`
--

DROP TABLE IF EXISTS `controles`;
CREATE TABLE `controles` (
  `idControl` int NOT NULL,
  `idPaciente` int DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `peso` double DEFAULT NULL,
  `altura` double DEFAULT NULL,
  `cintura` double DEFAULT NULL,
  `gasenergetico` double DEFAULT NULL,
  `IMC` double DEFAULT NULL,
  `proximacita` date DEFAULT NULL,
  `estado` tinyint DEFAULT NULL,
  `obs` text COLLATE utf8mb4_general_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dietacomidas`
--

DROP TABLE IF EXISTS `dietacomidas`;
CREATE TABLE `dietacomidas` (
  `iddietacomida` int NOT NULL,
  `idcomida` int DEFAULT NULL,
  `iddieta` int DEFAULT NULL,
  `porcion` int DEFAULT NULL,
  `horario` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;



-- -------------------------------------
--
-- Volcado de datos para la tabla `pacientes`
--

INSERT INTO `pacientes` (`idpaciente`, `nombre`, `dni`, `domicilio`, `telefono`, `estado`) VALUES
(1, 'Matteoni, Matias', 38825523, 'Chacabuco  dpto C', '47314867', 1),
(2, 'ENTESANO, LOUISINETTE', 95625820, 'ROSARIO', '3415558899', 1),
(3, 'Dominguez, Bernardo Dario Ismael', 30541575, 'La providencia Nro 62', '30541575', 1);


--
-- Volcado de datos para la tabla `dietas`
--

INSERT INTO `dietas` (`iddieta`, `nombre`, `idpaciente`, `fecinicio`, `fecfinal`, `pesoinicial`, `pesofinal`, `estado`) VALUES
(1, 'Salmón al horno con espárragos', 1, '2023-06-14', '2023-07-14', 186, 97, 1),
(2, 'DETOX', 2, '2023-10-27', '2023-11-02', 85, 75, 1),
(3,'Para el Lechon', 3, '2023-10-20', '2023-10-31',109.5, 90, 1);




--
-- Volcado de datos para la tabla `dietacomidas`
--

INSERT INTO `dietacomidas` (`iddietacomida`, `idcomida`, `iddieta`, `porcion`, `horario`) VALUES
(1, 5, 1, 4, 'Almuerzo'),
(2, 7, 2, 1, 'Almuerzo'),
(3, 7, 3, 1, 'Almuerzo');

--
-- Volcado de datos para la tabla `controles`
--

INSERT INTO `controles` (`idControl`,`idPaciente`, `fecha`, `peso`, `altura`, `cintura`, `gasenergetico`, `IMC`, `proximacita`, `estado`, `obs`) VALUES
(1, 1, '2023-10-27', 25, 1.6, 94, 16, 9.77, '2023-11-03', 1, 'Esta Perfecto el peso, pero no execedas el entrenamiento y ejercicios, procura no llegar a la fatiga'),
(2, 2, '2023-10-27', 95, 1.6, 80, 30, 37.11, '2023-11-03', 1, 'LA PACIENTE INGRESO CON UN INDICE CORPORAL ALTO PARA SU CONTEXTURA, SE LE ASIGNA LA DIETA DETOX CON CONTROL SEMANAL DUTRANTE 1 MES'),
(3, 2, '2023-11-03', 87, 1.6, 77, 30, 33.98, '2023-11-10', 1, 'PACIENTE MUESTRA PROGRESO POSITIVO EN CONTROL'),
(4, 2, '2023-11-10', 85, 1.6, 72, 30, 33.2, '2023-11-17', 1, 'LA PACIENTE SIGUE EL PROGRESO ESPERADO'),
(5, 2, '2023-11-17', 79, 1.6, 68, 30, 30.86, '2023-11-24', 1, 'SE ESPERABA MAYOR PROGRESO ESTA SEMANA'),
(6, 2, '2023-11-24', 75, 1.6, 62, 30, 29.3, '2023-12-02', 1, 'ASOMBROSAMENTE PERDIO 10 KG LA ULTIMA SEMANA Y LLEGO A LA META'),
(7, 3, '2023-11-20', 109.5, 1.75, 80, 30, 29.3, '2023-11-25', 1, 'El lechon pretende perder peso, sin dejar de comer porquerias ni cambiar su rutina de pasarsela sentado frente la PC'),
(8, 3, '2023-10-25', 112, 1.75, 85, 30, 29.3, '2023-10-31', 1, 'No esperabamos nada por parte del lechon y aun asi logra desepcionarnos, engordo el muy maldito!!!!');



-- --------------------------------------------------------


--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `comidas`
--
ALTER TABLE `comidas`
  ADD PRIMARY KEY (`idcomida`);

--
-- Indices de la tabla `controles`
--
ALTER TABLE `controles`
  ADD PRIMARY KEY (`idControl`),
  ADD KEY `idPaciente` (`idPaciente`);

--
-- Indices de la tabla `dietacomidas`
--
ALTER TABLE `dietacomidas`
  ADD PRIMARY KEY (`iddietacomida`),
  ADD KEY `dietacomidas_ibfk_1` (`iddieta`),
  ADD KEY `dietacomidas_ibfk_2` (`idcomida`);

--
-- Indices de la tabla `dietas`
--
ALTER TABLE `dietas`
  ADD PRIMARY KEY (`iddieta`),
  ADD KEY `idpaciente` (`idpaciente`);

--
-- Indices de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  ADD PRIMARY KEY (`idpaciente`),
  ADD UNIQUE KEY `dni_UNIQUE` (`dni`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `comidas`
--
ALTER TABLE `comidas`
  MODIFY `idcomida` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `controles`
--
ALTER TABLE `controles`
  MODIFY `idControl` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `dietacomidas`
--
ALTER TABLE `dietacomidas`
  MODIFY `iddietacomida` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `dietas`
--
ALTER TABLE `dietas`
  MODIFY `iddieta` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  MODIFY `idpaciente` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `controles`
--
ALTER TABLE `controles`
  ADD CONSTRAINT `controles_ibfk_1` FOREIGN KEY (`idPaciente`) REFERENCES `pacientes` (`idpaciente`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Filtros para la tabla `dietacomidas`
--
ALTER TABLE `dietacomidas`
  ADD CONSTRAINT `dietacomidas_ibfk_1` FOREIGN KEY (`iddieta`) REFERENCES `dietas` (`iddieta`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `dietacomidas_ibfk_2` FOREIGN KEY (`idcomida`) REFERENCES `comidas` (`idcomida`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Filtros para la tabla `dietas`
--
ALTER TABLE `dietas`
  ADD CONSTRAINT `dietas_ibfk_1` FOREIGN KEY (`idpaciente`) REFERENCES `pacientes` (`idpaciente`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
