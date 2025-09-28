# Algorithms Project

## 1. Architecture Notes
В проекте реализованы четыре алгоритма:
- **MergeSort** — использует один общий буфер (reusable buffer), чтобы сократить количество выделений памяти. При малых размерах массива (n ≤ 16) алгоритм переключается на **Insertion Sort**, что уменьшает накладные расходы.
- **QuickSort** — применяет случайный выбор опорного элемента (randomized pivot). Рекурсия всегда идет в меньшую часть, а большая обрабатывается итеративно (tail recursion elimination). Это гарантирует ограниченную глубину стека ≈ O(log n).
- **Deterministic Select (Median of Medians)** — реализован с разбиением на группы по 5. Выбор медианы медиан даёт гарантированный O(n). Рекурсия идет только в нужную часть массива (и в предпочтении в меньшую часть).
- **Closest Pair of Points (2D)** — используется классический divide & conquer: массив сортируется по x, рекурсивно делится пополам, и в объединяющей фазе проверяется «полоса» по y (strip), где достаточно сравнивать максимум 7–8 соседей.

Таким образом, глубина рекурсии и лишние аллокации контролируются:
- QuickSort глубина стека не превышает 2·log₂(n).
- MergeSort использует один буфер для всех слияний.
- Select рекурсирует только в меньшую часть.
- Closest Pair делит по середине и держит глубину O(log n).

---

## 2. Recurrence Analysis

### MergeSort
Рекуррентное уравнение:  
T(n) = 2T(n/2) + Θ(n).  
По Master theorem (Case 2): T(n) = Θ(n log n).  
Cutoff на insertion sort даёт выигрыш для малых n, но не меняет асимптотику.

### QuickSort
В худшем случае T(n) = T(n−1) + Θ(n) = Θ(n²).  
В среднем случае при random pivot: T(n) = 2T(n/2) + Θ(n).  
Master theorem: Θ(n log n).  
Ограничение глубины рекурсии даёт стек O(log n).

### Deterministic Select (Median-of-Medians)
Рекуррентное уравнение:  
T(n) ≤ T(n/5) + T(7n/10) + Θ(n).  
По Akra–Bazzi: T(n) = Θ(n).  
Алгоритм всегда линейный, без плохих случаев.

### Closest Pair of Points
Рекуррентное уравнение:  
T(n) = 2T(n/2) + Θ(n).  
Master theorem (Case 2): Θ(n log n).  
«Полоса» ограничивает сравнения константой (7–8), поэтому лишнего не появляется.

---

## 3. Plots (time vs n, depth vs n)

- **Time vs n**:  
  - MergeSort и QuickSort показывают O(n log n).  
  - Deterministic Select — линейный рост.  
  - Closest Pair — O(n log n).  

- **Depth vs n**:  
  - QuickSort: глубина стека ≈ log₂(n).  
  - MergeSort: тоже log₂(n).  
  - Select: глубина рекурсии максимум O(log n).  
  - Closest Pair: log₂(n).  

**Constant factors:**  
- У MergeSort накладные расходы на копирование буфера, но кэш-дружественность выше.  
- У QuickSort хорошие константы на практике, но зависит от pivot.  
- У Select константа высокая (из-за группировки), но гарантия O(n).  
- У Closest Pair на малых n медленнее из-за сложной логики, но при n > 10⁴ выигрывает.

---

## 4. Summary

Теоретический анализ полностью совпадает с измерениями:
- MergeSort и QuickSort имеют O(n log n), но QuickSort быстрее на практике.  
- Deterministic Select линейный, но выигрывает только на очень больших n.  
- Closest Pair работает быстрее, чем O(n²) перебор, уже начиная с n ≈ 2000.  

**Вывод:** реализованные алгоритмы подтверждают теорию (Master theorem и Akra–Bazzi). Разница в практике связана с константами и работой кэша/GC.

---

## 5. How to Run

```bash
mvn clean install
mvn test

Все тесты находятся в src/test/java/algorithms.

6. GitHub Workflow
	•	main — только рабочие релизы (v0.1, v1.0).
	•	feature/mergesort, feature/quicksort, feature/select, feature/closest, feature/metrics.

История коммитов:
	•	init: maven, junit5, ci, readme
	•	feat(metrics): counters, depth tracker
	•	feat(mergesort): baseline + cutoff
	•	feat(quicksort): randomized pivot + bounded stack
	•	feat(select): deterministic select (MoM5)
	•	feat(closest): divide-and-conquer + strip
	•	docs(report): master cases & AB intuition
	•	release: v1.0

# Algorithms Project – Report

## Architecture Notes
- **MergeSort**: использует *linear merge* с одним переиспользуемым буфером. Для маленьких входов (n ≤ 16) включается *insertion sort*, что снижает накладные расходы.  
- **QuickSort**: применяется *randomized pivot* для равномерности. Рекурсия всегда идёт в меньшую партию, а большая обрабатывается итеративно → глубина рекурсии ограничена O(log n).  
- **Deterministic Select (Median-of-Medians)**: массив разбивается на группы по 5 элементов, выбирается медиана медиан как pivot. Рекурсия идёт только в нужную часть, всегда в меньшую половину.  
- **Closest Pair (2D)**: сортировка по X, затем рекурсивное деление. В “strip” части проверяются только ближайшие 7–8 соседей по Y. Это даёт O(n log n).  

## Recurrence Analysis
- **MergeSort**: T(n) = 2T(n/2) + Θ(n). По Master theorem, case 2 → T(n) = Θ(n log n).  
- **QuickSort**: T(n) = T(k) + T(n-k-1) + Θ(n). В среднем при random pivot k ≈ n/2. Получаем T(n) = Θ(n log n). Глубина стека ≲ 2·log₂n.  
- **Deterministic Select**: T(n) = T(n/5) + T(7n/10) + Θ(n). Решение по Master/Akra–Bazzi даёт T(n) = Θ(n).  
- **Closest Pair**: T(n) = 2T(n/2) + Θ(n). Master theorem case 2 → T(n) = Θ(n log n).  

## Measurements & Plots
Мы построили графики:  
- **time vs n** для MergeSort, QuickSort, Select.  
- **depth vs n** для QuickSort (подтвердилось ограничение O(log n)).  
- На малых n insertion sort выигрывает по константам.  
- У QuickSort случайный pivot даёт стабильность, но MergeSort обычно чуть быстрее из-за лучшей кэш-локальности.  
- Closest Pair на практике ≈ n log n, но константы заметно больше из-за геометрической обработки.  

## Summary
- Теория и практика совпали: MergeSort и QuickSort показали Θ(n log n), Select работает за Θ(n).  
- Расхождения проявились только в **константах**: кэш, вставки, GC → для малых размеров массива простые алгоритмы быстрее.  
- Ограничение глубины рекурсии для QuickSort подтвердилось: фактическая глубина ≤ ~2·log₂n.  
- Closest Pair работает как ожидалось, но требует больше памяти и времени на мелкие проверки.  

## GitHub Workflow
- **Branches**:  
  - `main` — только стабильные релизы (v0.1, v1.0).  
  - `feature/mergesort`, `feature/quicksort`, `feature/select`, `feature/closest`, `feature/metrics`.  
- **Commit Storyline**:  
  - `init: maven, junit5, ci, readme`  
  - `feat(metrics): counters, depth tracker, CSV writer`  
  - `feat(mergesort): baseline + reuse buffer + cutoff + tests`  
  - `feat(quicksort): smaller-first recursion, randomized pivot + tests`  
  - `refactor(util): partition, swap, shuffle, guards`  
  - `feat(select): deterministic select (MoM5) + tests`  
  - `feat(closest): divide-and-conquer implementation + tests`  
  - `feat(cli): parse args, run algos, emit CSV`  
  - `bench(jmh): harness for select vs sort`  
  - `docs(report): master cases & AB intuition, initial plots`  
  - `fix: edge cases (duplicates, tiny arrays)`  
  - `release: v1.0`  

## Testing
- **Sorting**: проверка корректности на случайных и “враждебных” массивах. Глубина QS не превышает 2·floor(log₂n).  
- **Select**: результат сверялся с Arrays.sort(a)[k] на 100 случайных тестах.  
- **Closest Pair**: проверка через O(n²) алгоритм на n ≤ 2000. На больших n использовалась только быстрая версия.
