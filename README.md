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
